package com.moviesearch.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    int add(T t) throws SQLException;
    List<T> getAll() throws SQLException;
    T get(int id) throws SQLException;
    int update(int id, String newName) throws SQLException;
    int delete(int id) throws SQLException;
}
