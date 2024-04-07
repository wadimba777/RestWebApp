package com.moviesearch.service;

import com.moviesearch.dao.DirectorDAO;
import com.moviesearch.model.Director;
import com.moviesearch.util.DatabaseConnection;
import lombok.Getter;

import java.util.List;

/**
 * Сервисный слой для управдения объектами Director
 */
public class DirectorService {
    @Getter
    private static final DirectorService directorService = new DirectorService();

    private DirectorService() {
    }

    private final DirectorDAO directorDAO = new DirectorDAO(DatabaseConnection.getConnection());

    public Director add(Director director) {
        return directorDAO.add(director);
    }

    public int delete(int id) {
        return directorDAO.delete(id);
    }

    public Director get(int id) {
        return directorDAO.get(id);
    }

    public int update(Director director, String newName) {
        return directorDAO.update(director.getId(), newName);
    }

    public List<Director> getAll() {
        return directorDAO.getAll();
    }
}
