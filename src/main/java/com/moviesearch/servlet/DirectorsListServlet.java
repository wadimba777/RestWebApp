package com.moviesearch.servlet;

import com.moviesearch.dao.DirectorDAO;
import com.moviesearch.dao.MovieDAO;
import com.moviesearch.model.Director;
import com.moviesearch.model.Movie;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

    @WebServlet(name = "DirectorsListServlet", urlPatterns = "/movies")
    public class DirectorsListServlet extends HttpServlet {
        private DirectorDAO directorDAO;

        @Override
        public void init() {
            directorDAO = new DirectorDAO();
        }

        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Director> directors = directorDAO.getAll();
            request.setAttribute("directors", directors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("directors.jsp");
            dispatcher.forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String directorIdToDelete = request.getParameter("id");
            if (directorIdToDelete != null) {
                directorDAO.removeById(Integer.parseInt(directorIdToDelete));
            }
            response.sendRedirect("directors");
        }
    }
