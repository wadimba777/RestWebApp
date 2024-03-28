package com.moviesearch.servlet;

import com.moviesearch.dao.DirectorDAO;
import com.moviesearch.model.Director;
import com.moviesearch.util.DatabaseConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DirectorsListServlet", urlPatterns = "/movies")
public class DirectorsListServlet extends HttpServlet {
    private transient DirectorDAO directorDAO;

    @Override
    public void init() {
        directorDAO = new DirectorDAO(DatabaseConnection.getConnection());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Director> directors;
        try {
            directors = directorDAO.getAll();
            request.setAttribute("directors", directors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("directors.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String directorIdToDelete = request.getParameter("id");
        if (directorIdToDelete != null) {
            try {
                directorDAO.delete(Integer.parseInt(directorIdToDelete));
                response.sendRedirect("directors");
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
