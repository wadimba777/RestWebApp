package com.moviesearch.dao;

import com.moviesearch.model.Director;
import com.moviesearch.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorDAO extends AbstractDAO<Director> {

    @Override
    public void add(Director director) {
        String query = "INSERT INTO directors(name) VALUES (?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, director.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Director> getAll() {
        List<Director> directors = new ArrayList<>();
        String query = "SELECT id, name FROM directors";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                directors.add(new Director(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return directors;
}

@Override
public Director getById(int id) {
    String query = "SELECT id, name FROM directors WHERE id = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Director(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return null;
}

    @Override
    public void update(int id, String newName) {
        String query = "UPDATE directors SET name = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newName);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
public boolean removeById(int id) {
    String query = "DELETE FROM directors WHERE id = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
}
