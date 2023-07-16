package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.exceptions.NotSavedSubEntityException;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource dataSource;

    private final static String QUERY = "\n" +
            "with u as (select * from chat.users limit ? offset ?)\n" +
            "select r.id room_id,r.name room_name,r.owner room_owner,u.id user_id,u.login user_login,\n" +
            "u.password user_password, m.id message_id from u left join chat.rooms r on u.id=r.owner "+
            "left join chat.messages m on m.author=u.id and m.room = r.id;";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> result = null;
        if (page >= 0 && size >= 0) {
            result = new ArrayList<>();
            try (Connection con = dataSource.getConnection(); PreparedStatement statement = con.prepareStatement(QUERY)
            ) {
                statement.setLong(1, size);
                statement.setLong(2, page);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Long userId = rs.getLong("user_id");
                    Optional<User> optionalUser = result.stream().filter(u -> userId.equals(u.getId())).findFirst();
                    User user;
                    if (optionalUser.isPresent()) {
                        user = optionalUser.get();
                    } else {
                        user = new User(userId, rs.getString("user_login"), rs.getString("user_password"), new ArrayList<>(), new ArrayList<>());
                        result.add(user);
                    }
                    List<Chatroom> createdRooms = user.getCreatedRooms();
                    List<Chatroom> socializedRooms = user.getSocializedRooms();

                    Long roomId = rs.getLong("room_id");

                    Chatroom room = new Chatroom(roomId, rs.getString("room_name"), null, null);

                    if(createdRooms.stream().noneMatch(r -> roomId.equals(r.getId()))){
                        if(user.getId().equals(rs.getLong("room_owner"))){
                            createdRooms.add(room);
                        }
                    }

                    if(socializedRooms.stream().noneMatch(r -> roomId.equals(r.getId()))){
                        if(rs.getLong("message_id") != 0){
                            socializedRooms.add(room);
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println("Wrong SQL request");
                e.printStackTrace();
            }
        }
        return result;
    }
}

