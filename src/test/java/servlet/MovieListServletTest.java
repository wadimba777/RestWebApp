package servlet;

import com.moviesearch.dao.MovieDAO;
import com.moviesearch.model.Movie;
import com.moviesearch.service.MovieService;
import com.moviesearch.servlet.MovieListServlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class MovieListServletTest {

    @InjectMocks
    private MovieListServlet servlet;

    @Mock
    private MovieService movieDAO;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDoGet() throws ServletException, IOException, SQLException {
        List<Movie> movies = Arrays.asList(
                new Movie(1, "Movie 1", 1),
                new Movie(2, "Movie 2", 1)
        );
        doReturn(movies).when(movieDAO).getAll();
        when(request.getRequestDispatcher("movies.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).setAttribute("movies", movies);
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void testDoPost() throws ServletException, IOException, SQLException {
        when(request.getParameter("id")).thenReturn("1");
        List<Movie> movies = Arrays.asList(new Movie(1, "Movie 1", 1));
        doReturn(movies).when(movieDAO).getAll();
        when(request.getRequestDispatcher("movies")).thenReturn(requestDispatcher);

        servlet.doPost(request, response);

        verify(movieDAO, times(1)).delete(1);
        verify(response, times(1)).sendRedirect("movies");
    }
}
