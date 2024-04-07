package com.moviesearch.servlet;

import com.moviesearch.model.Director;
import com.moviesearch.service.DirectorService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Сервлет для добавления режиссера.
 */
@WebServlet(name = "AddDirectorServlet", urlPatterns = "/addDirector")
public class AddDirectorServlet extends HttpServlet {

    private transient DirectorService directorService;

    /**
     * Инициализирует объект DirectorDAO при запуске сервлета.
     */
    @Override
    public void init() {
        directorService = DirectorService.getDirectorService();
    }

    /**
     * Отображает страницу добавления режиссера.
     *
     * @param request  запрос
     * @param response ответ
     * @throws IOException если произошла ошибка ввода/вывода
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("addDirector.jsp");
    }

    /**
     * Обрабатывает запрос на добавление режиссера.
     *
     * @param request  запрос
     * @param response ответ
     * @throws IOException      если произошла ошибка ввода/вывода
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");

        Director director = new Director();
        director.setName(name);

        directorService.add(director);

        response.sendRedirect("director-added.jsp");
    }
}