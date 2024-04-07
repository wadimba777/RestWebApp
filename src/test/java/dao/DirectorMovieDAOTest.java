package dao;

import com.moviesearch.dao.DirectorDAO;
import com.moviesearch.dao.DirectorMovieDAO;
import com.moviesearch.dao.MovieDAO;
import com.moviesearch.model.Director;
import com.moviesearch.model.DirectorMovie;
import com.moviesearch.model.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class DirectorMovieDAOTest {

    private final String TRUNCATE_DIRECTORS_MOVIES = "TRUNCATE TABLE directors_movies RESTART IDENTITY CASCADE";

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withInitScript("init.sql");

    private Connection connection;
    private DirectorMovieDAO directorMovieDAO;
    private DirectorDAO directorDAO;
    private MovieDAO movieDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        String jdbcUrl = postgres.getJdbcUrl();
        String username = postgres.getUsername();
        String password = postgres.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, username, password);

        directorMovieDAO = new DirectorMovieDAO(connection);
        directorDAO = new DirectorDAO(connection);
        movieDAO = new MovieDAO(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        truncateTable(TRUNCATE_DIRECTORS_MOVIES);
        connection.close();
    }

    public void truncateTable(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addDirectorMovie_ShouldAddDirectorMovieToDatabase() {
        Director director = new Director(1, "Test Director");
        directorDAO.add(director);
        Movie movie = new Movie(1, "Test Movie", 1);
        movieDAO.add(movie);

        DirectorMovie directorMovie = new DirectorMovie(1, director.getId(), movie.getId());
        directorMovieDAO.add(directorMovie);

        DirectorMovie result = directorMovieDAO.get(directorMovie.getId());

        assertEquals(directorMovie, result);
    }

    @Test
    void getAllDirectorMovies_ShouldRetrieveAllDirectorMoviesFromDatabase() {
        Director director = new Director(1, "Test Director");
        directorDAO.add(director);
        Movie movie = new Movie(1, "Test Movie", 1);
        movieDAO.add(movie);

        DirectorMovie directorMovie1 = new DirectorMovie(1, director.getId(), movie.getId());
        DirectorMovie directorMovie2 = new DirectorMovie(2, director.getId(), movie.getId());
        directorMovieDAO.add(directorMovie1);
        directorMovieDAO.add(directorMovie2);

        List<DirectorMovie> result = directorMovieDAO.getAll();

        assertEquals(2, result.size());
    }

    @Test
    void getDirectorMovieById_ShouldRetrieveDirectorMovieByIdFromDatabase() {
        Director director = new Director(1, "Test Director");
        directorDAO.add(director);
        Movie movie = new Movie(1, "Test Movie", 1);
        movieDAO.add(movie);

        DirectorMovie directorMovie = new DirectorMovie(1, director.getId(), movie.getId());
        directorMovieDAO.add(directorMovie);

        DirectorMovie result = directorMovieDAO.get(directorMovie.getId());

        assertEquals(directorMovie, result);
    }

    @Test
    void deleteDirectorMovie_ShouldDeleteDirectorMovieFromDatabase() {
        Director director = new Director(1, "Test Director");
        directorDAO.add(director);
        Movie movie = new Movie(1, "Test Movie", 1);
        movieDAO.add(movie);

        DirectorMovie directorMovie = new DirectorMovie(1, director.getId(), movie.getId());
        directorMovieDAO.add(directorMovie);

        int result = directorMovieDAO.delete(directorMovie.getId());

        assertEquals(1, result);

        assertNull(directorMovieDAO.get(directorMovie.getId()));
    }
}
