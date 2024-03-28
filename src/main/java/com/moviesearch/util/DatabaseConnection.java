package com.moviesearch.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    public static final String URL = "jdbc:postgresql://localhost:5432/movielist_db";
    public static final String USER = "postgres";
    public static final String PASSWORD = "y2k";

    public static Connection getConnection() {
        Connection connection;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
