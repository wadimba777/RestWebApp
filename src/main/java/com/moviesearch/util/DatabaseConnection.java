package com.moviesearch.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Утилитарный класс для установления соединения с базой данных.
 */
public class DatabaseConnection {

    /** URL для подключения к базе данных. */
    public static final String URL = "jdbc:postgresql://localhost:5432/movielist_db";

    /** Имя пользователя базы данных. */
    public static final String USER = "postgres";

    /** Пароль пользователя базы данных. */
    public static final String PASSWORD = "y2k";

    /**
     * Получает соединение с базой данных.
     *
     * @return объект Connection для соединения с базой данных
     * @throws RuntimeException если произошла ошибка при установлении соединения
     */
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
