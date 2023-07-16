package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        String SQL_QUERY = "select * from chat.messages m LEFT JOIN (select id author_id, login author_login, password author_password from chat.users) u ON m.author = u.author_id"
                + " LEFT JOIN (select id room_id, name room_name from chat.rooms) r ON m.room = r.room_id" +
                " where m.id = " + id + ";";
        Message message = null;
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User author = new User(rs.getLong("author_id"), rs.getString("author_login"), rs.getString("author_password"), null, null);

                Chatroom chatroom = new Chatroom(rs.getLong("room_id"), rs.getString("room_name"), null, null);

                message = new Message(id, author, chatroom, rs.getString("text"), rs.getTimestamp("dateTime"));
            }
        } catch (SQLException e) {
            System.out.println("Wrong SQL request");
        }
        return Optional.ofNullable(message);
    }

    @Override
    public void save(Message message) {
        if (message.getAuthor() == null || message.getRoom() == null) {
            throw new NotSavedSubEntityException();
        } else {
            String AUTHOR_QUERY = "select * from chat.users u where u.id = " + message.getAuthor().getId() + ";";
            String ROOM_QUERY = "select * from chat.rooms r where r.id = " + message.getRoom().getId() + ";";
            try (Connection con = dataSource.getConnection()) {
                if (checkIsNotExists(AUTHOR_QUERY, con) || checkIsNotExists(ROOM_QUERY, con)) {
                    throw new NotSavedSubEntityException();
                }
                saveToDbAndReturnId(con,message);
            } catch (SQLException e) {
                System.out.println("Wrong SQL request");
                e.printStackTrace();
            }
        }
    }

    private boolean checkIsNotExists(String SQL_QUERY, Connection con) throws SQLException {
        PreparedStatement pst = con.prepareStatement(SQL_QUERY);
        ResultSet rs = pst.executeQuery();
        return !rs.next();
    }

    private void saveToDbAndReturnId(Connection con, Message message) throws SQLException {
        String SQL_INSERT = "insert into chat.messages(author, room, text, datetime) values ";
        String VALUES = String.format("(%d, %d, '%s', '%s');", message.getAuthor().getId(), message.getRoom().getId(),
                message.getText(), new SimpleDateFormat("MM-dd-yyyy HH:mm").format(message.getDateTime()));
        try(PreparedStatement statement = con.prepareStatement(SQL_INSERT + VALUES,
                Statement.RETURN_GENERATED_KEYS)){
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    message.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        }
    }
}
