package edu.school21.chat;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_SCHEMA = "schema.sql";
    private static final String DB_DATA = "data.sql";

    public static void main(String[] args) {
        try {
            Connection connection = getNewConnection();

            System.out.println("Create tables");
            executeQueriesFromFile(connection, DB_SCHEMA);
            System.out.println("Tables created");

            System.out.println("Filling tables");
            executeQueriesFromFile(connection, DB_DATA);
            System.out.println("Table filled");
        }catch (SQLException e){
            System.out.println("Can not connect to db");
            e.printStackTrace();
        } catch (FileNotFoundException e){
            System.out.println("Can not find sql files");
        }
    }

    private static Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private static void executeQueriesFromFile(Connection connection, String filename) throws FileNotFoundException {
        try( Scanner scanner = new Scanner(
                new File(System.getProperty("user.dir") + "/src/main/resources/" + filename))
                .useDelimiter(";")){
            try {
                while (scanner.hasNext()) {
                    connection.createStatement().execute(scanner.next());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

//    mvn clean compile assembly:single && java -jar target/chat-1.0-SNAPSHOT-jar-with-dependencies.jar