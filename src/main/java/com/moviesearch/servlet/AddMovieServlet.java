package com.moviesearch.servlet;

import java.io.IOException;

import com.moviesearch.dao.DirectorDAO;
import com.moviesearch.dao.DirectorMovieDAO;
import com.moviesearch.dao.MovieDAO;
import com.moviesearch.model.Movie;
import com.moviesearch.model.Director;
import com.moviesearch.util.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервлет для добавления фильма.
 */
@WebServlet(name = "AddMovieServlet", urlPatterns = "/addMovie")
public class AddMovieServlet extends HttpServlet {

    private transient DirectorDAO directorDAO;
    private transient MovieDAO movieDAO;
//    private transient DirectorMovieDAO directorMovieDAO;

    /**
     * Инициализирует объекты DirectorDAO и MovieDAO при запуске сервлета.
     */
    @Override
    public void init() {
        directorDAO = new DirectorDAO(DatabaseConnection.getConnection());
        movieDAO = new MovieDAO(DatabaseConnection.getConnection());
//        directorMovieDAO = new DirectorMovieDAO(DatabaseConnection.getConnection());
    }

    /**
     * Отображает страницу добавления фильма и передает список всех режиссеров.
     *
     * @param request  запрос
     * @param response ответ
     * @throws ServletException если произошла ошибка сервлета
     * @throws IOException      если произошла ошибка ввода/вывода
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Director> directors;
        try {
            directors = directorDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("directors", directors);
        request.getRequestDispatcher("addMovie.jsp").forward(request, response);
    }

    /**
     * Обрабатывает запрос на добавление фильма.
     *
     * @param request  запрос
     * @param response ответ
     * @throws IOException      если произошла ошибка ввода/вывода
     * @throws RuntimeException если произошла ошибка выполнения SQL-запроса
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        int directorId = Integer.parseInt(request.getParameter("directorId"));

        Director director;
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
