package com.moviesearch.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {

    public static final String URL = "jdbc:postgresql://localhost:5432/movielist_db"; //env.get("DB_URL");
    public static final String USER = "postgres"; //env.get("DB_USER");
    public static final String PASSWORD = "y2k" ;//env.get("DB_PASSWORD");

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Failed to connect to db: " + e);
        }
        return connection;
    }
}
