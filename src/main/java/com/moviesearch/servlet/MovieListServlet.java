package com.moviesearch.servlet;

import com.moviesearch.dao.MovieDAO;
import com.moviesearch.model.Movie;
import com.moviesearch.util.DatabaseConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Сервлет для отображения списка фильмов и их удаления.
 */
@WebServlet(name = "MovieListServlet", urlPatterns = "/movies")
public class MovieListServlet extends HttpServlet {
    private transient MovieDAO movieDAO;

    @Override
    public void init() {
        movieDAO = new MovieDAO(DatabaseConnection.getConnection());
    }

    /**
     * Обработчик HTTP GET запроса для отображения списка фильмов.
     *
     * @param request  объект HttpServletRequest
     * @param response объект HttpServletResponse
     * @throws ServletException если произошла ошибка сервлета
     * @throws IOException      если произошла ошибка ввода-вывода
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> movies;
        movies = movieDAO.getAll();

        request.setAttribute("movies", movies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("movies.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Обработчик HTTP POST запроса для удаления фильма из списка.
     *
     * @param request  объект HttpServletRequest
     * @param response объект HttpServletResponse
     * @throws IOException если произошла ошибка ввода-вывода
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String movieIdToDelete = request.getParameter("id");
        if (movieIdToDelete != null) {
            movieDAO.delete(Integer.parseInt(movieIdToDelete));
        }
        response.sendRedirect("movies");
    }
}
