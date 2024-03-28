package com.moviesearch.dao;

import com.moviesearch.model.Movie;
import com.moviesearch.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements DAO<Movie> {

    private final Connection connection;

    public MovieDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(Movie movie) throws SQLException {
        String query = "INSERT INTO movies(title, director_id) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, movie.getTitle());
            statement.setInt(2, movie.getDirectorId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

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

    @Override
    public int update(int id, String newTitle) throws SQLException {
        String query = "UPDATE movies SET title = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newTitle);
            statement.setInt(2, id);
            return statement.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String query = "DELETE FROM movies WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }
}
