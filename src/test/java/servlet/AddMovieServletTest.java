package servlet;

import com.moviesearch.model.Director;
import com.moviesearch.model.DirectorMovie;
import com.moviesearch.model.Movie;
import com.moviesearch.service.DirectorMovieService;
import com.moviesearch.service.DirectorService;
import com.moviesearch.service.MovieService;
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
    private DirectorService directorService;

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
    void testDoPost() throws IOException {
        String title = "Test Movie";
        int directorId = 1;
        Director director = new Director(directorId, "Director Name");
        Movie movie = new Movie(1, title, directorId);

        when(request.getParameter("title")).thenReturn(title);
        when(request.getParameter("directorId")).thenReturn(String.valueOf(directorId));
        when(directorService.get(directorId)).thenReturn(director);
        when(movieService.add(any(Movie.class))).thenReturn(movie);

        servlet.doPost(request, response);

        verify(movieService, times(1)).add(any(Movie.class));
        verify(directorMovieService, times(1)).add(any(DirectorMovie.class));
        verify(response, times(1)).sendRedirect("movie-added.jsp");
    }



    @Test
    void testDoGet() throws IOException, ServletException, SQLException {
        List<Director> directors = Arrays.asList(
                new Director(1, "Director 1"),
                new Director(2, "Director 2")
        );
        when(directorService.getAll()).thenReturn(directors);
        when(request.getRequestDispatcher("addMovie.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).setAttribute("directors", directors);
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
