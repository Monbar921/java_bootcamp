package edu.school21.chat.app;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.HikariPoolBean;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Optional;

public class Program {
    public static void main(String[] args) {
        HikariPoolBean hk = new HikariPoolBean();
        if(hk.getDataSource() != null){
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(hk.getDataSource());
            Optional<Message> messageOptional = messagesRepository.findById(1l);
            if (messageOptional.isPresent()) {
                Message message = messageOptional.get();
                message.setText("Bye");
                message.setDateTime(null);
                messagesRepository.update(message);
            }
        } else {
            System.out.println("Wrong db settings");
        }
    }
}


//    mvn clean compile assembly:single && java -jar target/chat-1.0-SNAPSHOT-jar-with-dependencies.jar