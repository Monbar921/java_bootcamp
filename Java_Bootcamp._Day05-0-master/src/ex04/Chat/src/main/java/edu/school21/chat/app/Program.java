package edu.school21.chat.app;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;
import edu.school21.chat.repositories.pool.HikariPoolBean;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.List;


public class Program {
    public static void main(String[] args) {
        HikariPoolBean hk = new HikariPoolBean();
        if(hk.getDataSource() != null){
            UsersRepository usersRepository = new UsersRepositoryJdbcImpl(hk.getDataSource());
            List<User> users = usersRepository.findAll(0, 2);
            users.forEach(System.out::println);

            System.out.println("\nAnother options");
            users = usersRepository.findAll(4, 1);
            users.forEach(System.out::println);
        } else {
            System.out.println("Wrong db settings");
        }
    }
}


//    mvn clean compile assembly:single && java -jar target/chat-1.0-SNAPSHOT-jar-with-dependencies.jar