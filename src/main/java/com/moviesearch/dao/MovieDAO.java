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
     */
    @Override
    public Movie add(Movie movie)   {
        String query = "INSERT INTO movies(title, director_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, movie.getTitle());
            statement.setInt(2, movie.getDirectorId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                movie.setId(generatedKeys.getInt("id"));
            }
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Возвращает список всех фильмов из базы данных.
     *
     * @return список всех фильмов
     */
    @Override
    public List<Movie> getAll() {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Возвращает фильм по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор фильма
     * @return объект фильма с указанным идентификатором, или null, если фильм не найден
     */
    @Override
    public Movie get(int id) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Обновляет название фильма с указанным идентификатором.
     *
     * @param id       уникальный идентификатор фильма
     * @param newTitle новое название фильма
     * @return количество обновленных строк в базе данных
     */
    @Override
    public int update(int id, String newTitle) {
        String query = "UPDATE movies SET title = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newTitle);
            statement.setInt(2, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Удаляет фильм с указанным идентификатором из базы данных.
     *
     * @param id уникальный идентификатор фильма для удаления
     * @return количество удаленных строк в базе данных
     */
    @Override
    public int delete(int id) {
        String query = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
