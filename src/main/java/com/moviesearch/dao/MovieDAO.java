package com.moviesearch.dao;

import com.moviesearch.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Реализация интерфейса DAO для работы с объектами типа Movie.
 */
public class MovieDAO implements DAO<Movie> {

    private final Connection connection;

    /**
     * Создает новый объект MovieDAO с указанным соединением к базе данных.
     *
     * @param connection соединение к базе данных
     */
    public MovieDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Добавляет новый фильм в базу данных.
     *
     * @param movie объект фильма для добавления
     * @return количество добавленных строк в базу данных
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    @Override
    public int add(Movie movie) throws SQLException {
        String query = "INSERT INTO movies(title, director_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, movie.getTitle());
            statement.setInt(2, movie.getDirectorId());
            return statement.executeUpdate();
        }
    }

    /**
     * Возвращает список всех фильмов из базы данных.
     *
     * @return список всех фильмов
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    @Override
    public List<Movie> getAll() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT id, title, director_id FROM movies";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("director_id")
                ));
            }
            return movies;
        }
    }

    /**
     * Возвращает фильм по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор фильма
     * @return объект фильма с указанным идентификатором, или null, если фильм не найден
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    @Override
    public Movie get(int id) throws SQLException {
        Movie movie = null;
        String query = "SELECT id, title, director_id FROM movies WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int movieId = resultSet.getInt("id");
                String movieTitle = resultSet.getString("title");
                int directorId = resultSet.getInt("director_id");
                movie = new Movie(movieId, movieTitle, directorId);
            }
            return movie;
        }
    }

    /**
     * Обновляет название фильма с указанным идентификатором.
     *
     * @param id       уникальный идентификатор фильма
     * @param newTitle новое название фильма
     * @return количество обновленных строк в базе данных
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    @Override
    public int update(int id, String newTitle) throws SQLException {
        String query = "UPDATE movies SET title = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newTitle);
            statement.setInt(2, id);
            return statement.executeUpdate();
        }
    }

    /**
     * Удаляет фильм с указанным идентификатором из базы данных.
     *
     * @param id уникальный идентификатор фильма для удаления
     * @return количество удаленных строк в базе данных
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    @Override
    public int delete(int id) throws SQLException {
        String query = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }
}
