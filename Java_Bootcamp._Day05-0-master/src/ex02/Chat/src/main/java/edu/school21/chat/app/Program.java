package edu.school21.chat.app;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.HikariPoolBean;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        HikariPoolBean hk = new HikariPoolBean();
        if(hk.getDataSource() != null){
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(hk.getDataSource());
            User author = new User(1, "Bob", "000000", new ArrayList<Chatroom>() ,new ArrayList<Chatroom>());

            Chatroom chatroom = new Chatroom(1,"Random", author, new ArrayList<Message>());

            Message message = new Message(null, author, chatroom, "Hello!", Timestamp.valueOf(LocalDateTime.now()));
            messagesRepository.save(message);

            System.out.println(message.getId());
        } else {
            System.out.println("Wrong db settings");
        }
    }
}


//    mvn clean compile assembly:single && java -jar target/chat-1.0-SNAPSHOT-jar-with-dependencies.jar