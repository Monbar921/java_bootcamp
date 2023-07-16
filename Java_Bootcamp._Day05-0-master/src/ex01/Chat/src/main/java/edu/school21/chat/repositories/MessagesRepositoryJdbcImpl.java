package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
                message = new Message(id);

                User author = new User();
                author.setId(rs.getLong("author_id"));
                author.setLogin(rs.getString("author_login"));
                author.setPassword(rs.getString("author_password"));
                message.setAuthor(author);

                Chatroom chatroom = new Chatroom();
                chatroom.setId(rs.getLong("room_id"));
                chatroom.setName(rs.getString("room_name"));
                message.setRoom(chatroom);

                message.setText(rs.getString("text"));
                message.setDateTime(rs.getTimestamp("dateTime"));
            }
        } catch (SQLException e) {
            System.out.println("Wrong SQL request");
        }

        return Optional.ofNullable(message);
    }
}
