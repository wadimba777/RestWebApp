package com.moviesearch.dao;

import com.moviesearch.model.Director;
import com.moviesearch.model.DirectorMovie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorMovieDAO implements DAO<DirectorMovie> {

    private final Connection connection;

    public DirectorMovieDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public DirectorMovie add(DirectorMovie directorMovie) {
        String query = "INSERT INTO directors_movies(director_id, movie_id) VALUES(?, ?)";

        try (PreparedStatement statement = connection
                .prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, directorMovie.getDirectorId());
            statement.setInt(2, directorMovie.getMovieId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                directorMovie.setId(generatedKeys.getInt("id"));
            }
            return directorMovie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DirectorMovie> getAll() {
        List<DirectorMovie> direcrtorsMovies = new ArrayList<>();
        String query = "SELECT id, director_id, movie_id FROM directors_movies";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                direcrtorsMovies.add(new DirectorMovie(
                        resultSet.getInt("id"),
                        resultSet.getInt("director_id"),
                        resultSet.getInt("movie_id")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return direcrtorsMovies;
    }

    @Override
    public DirectorMovie get(int id) {
        DirectorMovie directorMovie = null;
        String query = "SELECT id, director_id, movie_id FROM directors_movies WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                directorMovie = new DirectorMovie(
                        resultSet.getInt("id"),
                        resultSet.getInt("director_id"),
                        resultSet.getInt("movie_id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return directorMovie;
    }

    @Override
    public int update(int id, String newName) {
        throw new UnsupportedOperationException("Update operation is not supported for this table");
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM directors_movies WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
