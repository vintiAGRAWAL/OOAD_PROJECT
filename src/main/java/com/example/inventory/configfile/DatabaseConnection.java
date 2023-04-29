package com.example.inventory.configfile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {

    private static volatile Connection connection;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public Connection getConnection() {
        if (connection == null) {
            synchronized (DatabaseConnection.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(databaseUrl, username, password);
                    } catch (SQLException e) {
                        throw new RuntimeException("Error connecting to the database", e);
                    }
                }
            }
        }
        return connection;
    }
}