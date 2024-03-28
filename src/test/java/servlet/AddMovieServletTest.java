package servlet;

import com.moviesearch.dao.DirectorDAO;
import com.moviesearch.dao.MovieDAO;
import com.moviesearch.model.Director;
import com.moviesearch.model.Movie;
import com.moviesearch.servlet.AddMovieServlet;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class AddMovieServletTest {

    @InjectMocks
    private AddMovieServlet servlet;

    @Mock
    private DirectorDAO directorDAO;

    @Mock
    private MovieDAO movieDAO;

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
    void testDoPost() throws IOException, SQLException {
        String title = "Test Movie";
        int directorId = 1;
        Director director = new Director(directorId, "Director 1");
        when(request.getParameter("title")).thenReturn(title);
        when(request.getParameter("directorId")).thenReturn(String.valueOf(directorId));
        when(directorDAO.get(directorId)).thenReturn(director);
        doReturn(1).when(movieDAO).add(any(Movie.class));

        servlet.doPost(request, response);

        verify(movieDAO, times(1))
                .add(argThat(movie -> movie.getTitle().equals(title)
                        && movie.getDirectorId() == directorId)
                );
        verify(response, times(1))
                .sendRedirect("movie-added.jsp");
    }

    @Test
    void testDoGet() throws IOException, ServletException, SQLException {
        List<Director> directors = Arrays.asList(
                new Director(1, "Director 1"),
                new Director(2, "Director 2")
        );
        when(directorDAO.getAll()).thenReturn(directors);
        when(request.getRequestDispatcher("addMovie.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).setAttribute("directors", directors);
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
