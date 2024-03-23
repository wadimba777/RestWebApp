package com.moviesearch.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.moviesearch.util.VariableLoader.PASSWORD;
import static com.moviesearch.util.VariableLoader.URL;
import static com.moviesearch.util.VariableLoader.USER;

public class DatabaseConnection {

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
