package com.moviesearch.servlet;

import java.io.IOException;

import com.moviesearch.dao.DirectorDAO;
import com.moviesearch.dao.MovieDAO;
import com.moviesearch.model.Movie;
import com.moviesearch.model.Director;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddMovieServlet", urlPatterns = "/addMovie")
public class AddMovieServlet extends HttpServlet {

    private transient DirectorDAO directorDAO;
    private transient MovieDAO movieDAO;

    @Override
    public void init() {
        directorDAO = new DirectorDAO();
        movieDAO = new MovieDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Director> directors = null;
        try {
            directors = directorDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("directors", directors);
        request.getRequestDispatcher("addMovie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        int directorId = Integer.parseInt(request.getParameter("directorId"));

        Director director = null;
        try {
            director = directorDAO.get(directorId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDirectorId(director.getId());

        try {
            movieDAO.add(movie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("movie-added.jsp");
    }
}
