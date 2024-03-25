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
import java.util.List;

@WebServlet(name = "AddMovieServlet", urlPatterns = "/addMovie")
public class AddMovieServlet extends HttpServlet {

    private DirectorDAO directorDAO;
    private MovieDAO movieDAO;

    @Override
    public void init() {
        directorDAO = new DirectorDAO();
        movieDAO = new MovieDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Director> directors = directorDAO.getAll();
        request.setAttribute("directors", directors);
        request.getRequestDispatcher("addMovie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        int directorId = Integer.parseInt(request.getParameter("directorId"));

        Director director = directorDAO.getById(directorId);

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDirectorId(director.getId());

        movieDAO.add(movie);

        response.sendRedirect("movie-added.jsp");
    }
}
