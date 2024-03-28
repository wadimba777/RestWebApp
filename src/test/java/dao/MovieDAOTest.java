package dao;

import com.moviesearch.dao.DirectorDAO;
import com.moviesearch.dao.MovieDAO;
import com.moviesearch.model.Director;
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

import static org.junit.Assert.assertEquals;

@Testcontainers
public class MovieDAOTest {

    private final String TRUNCATE_MOVIES = "TRUNCATE TABLE movies RESTART IDENTITY CASCADE";

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withInitScript("init.sql");

    private Connection connection;
    private MovieDAO movieDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        String jdbcUrl = postgres.getJdbcUrl();
        String username = postgres.getUsername();
        String password = postgres.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, username, password);

        Director director = new Director(1, "Test Director");  // Test director for movie creation
        DirectorDAO directorDAO = new DirectorDAO(connection);
        directorDAO.add(director);

        movieDAO = new MovieDAO(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        truncateTable(TRUNCATE_MOVIES);
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
    void addMovie_ShouldAddMovieToDatabase() throws SQLException {
        Movie movie = new Movie(1, "Test Movie", 1);

        int result = movieDAO.add(movie);

        assertEquals(1, result);
    }

    @Test
    void getAllMovies_ShouldRetrieveAllMoviesFromDatabase() throws SQLException {
        Movie movie1 = new Movie(1, "Movie 1", 1);
        Movie movie2 = new Movie(2, "Movie 2", 1);
        movieDAO.add(movie1);
        movieDAO.add(movie2);

        List<Movie> movies = movieDAO.getAll();

        assertEquals(2, movies.size());
    }

    @Test
    void getMovieById_ShouldRetrieveMovieByIdFromDatabase() throws SQLException {
        Movie movie = new Movie(1, "Test Movie", 1);
        movieDAO.add(movie);

        Movie retrievedMovie = movieDAO.get(movie.getId());

        assertEquals(movie, retrievedMovie);
    }

    @Test
    void updateMovie_ShouldUpdateMovieInDatabase() throws SQLException {
        Movie movie = new Movie(1, "Test Movie", 1);
        movieDAO.add(movie);

        int result = movieDAO.update(1, "Updated Movie");

        assertEquals(1, result);
    }

    @Test
    void deleteMovie_ShouldDeleteMovieFromDatabase() throws SQLException {
        Movie movie = new Movie(1, "Test Movie", 1);
        movieDAO.add(movie);

        int result = movieDAO.delete(1);

        assertEquals(1, result);
    }
}
