package com.moviesearch.dao;

import com.moviesearch.model.Director;
import com.moviesearch.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorDAO implements DAO<Director> {

    private final Connection connection;

    public DirectorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(Director director) throws SQLException {
        String query = "INSERT INTO directors(name) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, director.getName());
            return statement.executeUpdate();
        }
    }

    @Override
    public List<Director> getAll() throws SQLException {
        List<Director> directors = new ArrayList<>();
        String query = "SELECT id, name FROM directors";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                directors.add(new Director(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
            return directors;
        }
    }

    @Override
    public Director get(int id) throws SQLException {
        Director director = null;
        String query = "SELECT id, name FROM directors WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query);) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int directorId = resultSet.getInt("id");
                String directorName = resultSet.getString("name");
                director = new Director(directorId, directorName);
            }
            return director;
        }
    }

    @Override
    public int update(int id, String newName) throws SQLException {
        String query = "UPDATE directors SET name = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newName);
            statement.setInt(2, id);
            return statement.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String query = "DELETE FROM directors WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }
}
