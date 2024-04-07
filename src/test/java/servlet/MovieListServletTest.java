package servlet;

import com.moviesearch.dao.MovieDAO;
import com.moviesearch.model.Director;
import com.moviesearch.model.DirectorMovie;
import com.moviesearch.model.Movie;
import com.moviesearch.service.MovieService;
import com.moviesearch.service.DirectorMovieService;
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
    private DirectorMovieService directorMovieService;

    @Mock
    private MovieService movieService;

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
    void testDoGet() throws ServletException, IOException {
        Movie movie = new Movie(1, "Test Movie", 1);
        Movie movie2 = new Movie(2, "Test Movie: 2", 1);
        Director director = new Director(1, "Test Director");

        List<DirectorMovie> movies = Arrays.asList(
                new DirectorMovie(1, director.getId(), movie.getId()),
                new DirectorMovie(2, director.getId(), movie2.getId())
        );
        when(directorMovieService.getAll()).thenReturn(movies);
        when(request.getRequestDispatcher("movies.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).setAttribute("movies", movies);
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void testDoPost() throws IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(movieService.get(1)).thenReturn(new Movie(1, "Test Title", 1));
        when(directorMovieService.get(1)).thenReturn(new DirectorMovie(1, 1, 1));

        servlet.doPost(request, response);

        verify(directorMovieService, times(1)).delete(1);
        verify(response, times(1)).sendRedirect("movies");
    }
}
