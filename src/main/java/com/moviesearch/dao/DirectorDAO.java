package com.moviesearch.dao;

import com.moviesearch.model.Director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса DAO для работы с объектами типа Director.
 */
public class DirectorDAO implements DAO<Director> {

    private final Connection connection;

    /**
     * Создает новый объект DirectorDAO с указанным соединением к базе данных.
     *
     * @param connection соединение к базе данных
     */
    public DirectorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Director add(Director director) {
        String query = "INSERT INTO directors(name) VALUES (?)";

        try (PreparedStatement statement = connection
                .prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, director.getName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                director.setId(generatedKeys.getInt("id"));
            }
            return director;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Director> getAll() {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Director get(int id) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(int id, String newName) {
        String query = "UPDATE directors SET name = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newName);
            statement.setInt(2, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM directors WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
