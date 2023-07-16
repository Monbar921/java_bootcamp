package edu.school21.chat.app;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.HikariPoolBean;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Program {
    public static void main(String[] args) {
        HikariPoolBean hk = new HikariPoolBean();
        if(hk.getDataSource() != null){
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(hk.getDataSource());
            System.out.println("Enter a message ID");
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
                Long messageId = Long.parseLong(reader.readLine());
               Optional<Message> message =  messagesRepository.findById(messageId);
                message.ifPresent(System.out::println);
            }catch (IOException e){

            } catch (NumberFormatException e){
                System.out.println("Give me a number");
            }
        } else {
            System.out.println("Wrong db settings");
        }
    }
}