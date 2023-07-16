package edu.school21.chat.repositories.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class HikariPoolBean {
    private static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "admin";
    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource = null;

    public HikariPoolBean(){
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        dataSource = new HikariDataSource(config);
   }

    public HikariDataSource getDataSource(){
        return dataSource;
    }
}
