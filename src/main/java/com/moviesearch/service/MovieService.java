package com.moviesearch.service;

import com.moviesearch.dao.MovieDAO;
import com.moviesearch.model.Movie;
import com.moviesearch.util.DatabaseConnection;

import java.util.List;

public class MovieService {
    private static final MovieService movieService = new MovieService();

    private MovieService() {
    }

    public static MovieService getMovieService() {
        return movieService;
    }

    private MovieDAO movieDAO = new MovieDAO(DatabaseConnection.getConnection());

    public Movie add(Movie movie) {
        return movieDAO.add(movie);
    }

    public int delete(int id) {
        return movieDAO.delete(id);
    }

    public Movie get(int id) {
        return movieDAO.get(id);
    }

    public int update(Movie movie, String newName) {
        return movieDAO.update(movie.getId(), newName);
    }

    public List<Movie> getAll() {
        return movieDAO.getAll();
    }
}
