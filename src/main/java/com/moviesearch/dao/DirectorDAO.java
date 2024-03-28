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

    /**
     * Добавляет нового режиссера в базу данных.
     *
     * @param director объект режиссера для добавления
     * @return количество добавленных строк в базу данных
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    @Override
    public int add(Director director) throws SQLException {
        String query = "INSERT INTO directors(name) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, director.getName());
            return statement.executeUpdate();
        }
    }

    /**
     * Возвращает список всех режиссеров из базы данных.
     *
     * @return список всех режиссеров
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
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

    /**
     * Возвращает режиссера по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор режиссера
     * @return объект режиссера с указанным идентификатором, или null, если режиссер не найден
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
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

    /**
     * Обновляет имя режиссера с указанным идентификатором.
     *
     * @param id      уникальный идентификатор режиссера
     * @param newName новое имя режиссера
     * @return количество обновленных строк в базе данных
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    @Override
    public int update(int id, String newName) throws SQLException {
        String query = "UPDATE directors SET name = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newName);
            statement.setInt(2, id);
            return statement.executeUpdate();
        }
    }

    /**
     * Удаляет режиссера с указанным идентификатором из базы данных.
     *
     * @param id уникальный идентификатор режиссера для удаления
     * @return количество удаленных строк в базе данных
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    @Override
    public int delete(int id) throws SQLException {
        String query = "DELETE FROM directors WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }
}
