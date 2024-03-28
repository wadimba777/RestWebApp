package com.moviesearch.servlet;
import com.moviesearch.model.Director;
import com.moviesearch.dao.DirectorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "AddDirectorServlet", urlPatterns = "/addDirector")
public class AddDirectorServlet extends HttpServlet {

    private transient DirectorDAO directorDAO;

    @Override
    public void init() {
        directorDAO = new DirectorDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("addDirector.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        Director director = new Director();
        director.setName(name);

        try {
            directorDAO.add(director);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("director-added.jsp");
    }
}
