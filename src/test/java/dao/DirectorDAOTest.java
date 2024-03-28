package dao;

import com.moviesearch.dao.DirectorDAO;
import com.moviesearch.model.Director;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class DirectorDAOTest {
    private final String  TRUNCATE_DIRECTORS = "TRUNCATE TABLE directors RESTART IDENTITY CASCADE";
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withInitScript("init.sql");

    private Connection connection;
    private DirectorDAO directorDAO;

    @BeforeEach
    void setUp() throws SQLException {
        String jdbcUrl = postgres.getJdbcUrl();
        String username = postgres.getUsername();
        String password = postgres.getPassword();
        connection = DriverManager.getConnection(jdbcUrl, username, password);
        directorDAO = new DirectorDAO(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        truncateTable(TRUNCATE_DIRECTORS);
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
    void addDirector_ShouldReturnOne_WhenDirectorAdded() throws SQLException {

        Director director = new Director(1, "Test Director");

        int result = directorDAO.add(director);

        assertEquals(1, result);
    }

    @Test
    void getAllDirectors_ShouldReturnCorrectNumberOfDirectors_WhenDirectorsAdded() throws SQLException {

        Director director1 = new Director(1, "Director 1");
        Director director2 = new Director(2, "Director 2");
        directorDAO.add(director1);
        directorDAO.add(director2);

        List<Director> directors = directorDAO.getAll();

        assertEquals(2, directors.size());
    }

    @Test
    void getDirectorById_ShouldReturnCorrectDirector_WhenValidIdProvided() throws SQLException {
        Director director1 = new Director(1, "Director 1");
        directorDAO.add(director1);

        Director result = directorDAO.get(1);

        assertEquals(director1, result);
    }

    @Test
    void updateDirector_ShouldUpdateDirectorName_WhenValidIdAndNewNameProvided() throws SQLException {
        Director director = new Director(1, "Old Director");
        directorDAO.add(director);

        int updatedRows = directorDAO.update(1, "New Director");

        assertEquals(1, updatedRows);
        Director updatedDirector = directorDAO.get(1);
        assertEquals("New Director", updatedDirector.getName());
    }

    @Test
    void deleteDirector_ShouldDeleteDirector_WhenValidIdProvided() throws SQLException {
        Director director = new Director(1, "Director to delete");
        directorDAO.add(director);

        int deletedRows = directorDAO.delete(1);

        assertEquals(1, deletedRows);
        assertEquals(0, directorDAO.getAll().size());
    }
}
