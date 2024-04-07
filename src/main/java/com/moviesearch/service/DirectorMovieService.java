package com.moviesearch.service;

import com.moviesearch.dao.DirectorMovieDAO;
import com.moviesearch.model.DirectorMovie;
import com.moviesearch.util.DatabaseConnection;
import lombok.Getter;

import java.util.List;

/**
 * Сервисный слой для управдения объектами DirectorMovie
 */
public class DirectorMovieService {
    @Getter
    private static final DirectorMovieService directorMovieService = new DirectorMovieService();

    private DirectorMovieService() {
    }

    private final DirectorMovieDAO directorMovieDAO = new DirectorMovieDAO(DatabaseConnection.getConnection());

    public DirectorMovie add(DirectorMovie directorMovie) {
        return directorMovieDAO.add(directorMovie);
    }

    public int delete(int id) {
        return directorMovieDAO.delete(id);
    }

    public DirectorMovie get(int id) {
        return directorMovieDAO.get(id);
    }

    public List<DirectorMovie> getAll() {
        return directorMovieDAO.getAll();
    }
}
