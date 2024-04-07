package com.moviesearch.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Интерфейс DAO (Data Access Object) предоставляет методы для доступа к данным.
 *
 * @param <T> тип объекта, с которым работает DAO
 */
public interface DAO<T> {

    /**
     * Добавляет новый объект в базу данных.
     *
     * @param t объект для добавления
     * @return новый объект класса T
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    T add(T t) throws SQLException;

    /**
     * Возвращает список всех объектов из базы данных.
     *
     * @return список всех объектов
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    List<T> getAll() throws SQLException;

    /**
     * Возвращает объект по его уникальному идентификатору.
     *
     * @param id уникальный идентификатор объекта
     * @return объект с указанным идентификатором
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    T get(int id) throws SQLException;

    /**
     * Обновляет имя объекта с указанным идентификатором.
     *
     * @param id      уникальный идентификатор объекта
     * @param newName новое имя объекта
     * @return количество обновленных строк в базе данных
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    int update(int id, String newName) throws SQLException;

    /**
     * Удаляет объект с указанным идентификатором из базы данных.
     *
     * @param id уникальный идентификатор объекта для удаления
     * @return количество удаленных строк в базе данных
     * @throws SQLException если произошла ошибка при выполнении SQL-запроса
     */
    int delete(int id) throws SQLException;
}
