package com.moviesearch.service;

import com.moviesearch.dao.DirectorMovieDAO;
import com.moviesearch.model.DirectorMovie;
import com.moviesearch.util.DatabaseConnection;

import java.util.List;

public class DirectorMovieService {
    private static final DirectorMovieService directorMovieService = new DirectorMovieService();

    private DirectorMovieService() {
    }

    public static DirectorMovieService getDirectorMovieService() {
        return directorMovieService;
    }

    private DirectorMovieDAO directorMovieDAO = new DirectorMovieDAO(DatabaseConnection.getConnection());

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
