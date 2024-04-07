package com.moviesearch.servlet;

import java.io.IOException;

import com.moviesearch.model.DirectorMovie;
import com.moviesearch.model.Movie;
import com.moviesearch.model.Director;
import com.moviesearch.service.DirectorMovieService;
import com.moviesearch.service.DirectorService;
import com.moviesearch.service.MovieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * Сервлет для добавления фильма.
 */
@WebServlet(name = "AddMovieServlet", urlPatterns = "/addMovie")
public class AddMovieServlet extends HttpServlet {

    private transient DirectorService directorService;
    private transient MovieService movieService;
   private transient DirectorMovieService directorMovieService;

    /**
     * Инициализирует объекты DirectorService и MovieService при запуске сервлета.
     */
    @Override
    public void init() {
        directorService = DirectorService.getDirectorService();
        movieService = MovieService.getMovieService();
        directorMovieService = DirectorMovieService.getDirectorMovieService();
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
        directors = directorService.getAll();

        request.setAttribute("directors", directors);
        request.getRequestDispatcher("addMovie.jsp").forward(request, response);
    }

    /**
     * Обрабатывает запрос на добавление фильма.
     *
     * @param request  запрос
     * @param response ответ
     * @throws IOException      если произошла ошибка ввода/вывода
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        int directorId = Integer.parseInt(request.getParameter("directorId"));

        Director director;
        director = directorService.get(directorId);

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDirectorId(director.getId());

        int movieId = movieService.add(movie).getId();


        if (movieId != 0) {
            DirectorMovie directorMovie = new DirectorMovie();
            directorMovie.setMovieId(movie.getId());
            directorMovie.setDirectorId(director.getId());

            directorMovieService.add(directorMovie);
        }

        response.sendRedirect("movie-added.jsp");
    }
}
