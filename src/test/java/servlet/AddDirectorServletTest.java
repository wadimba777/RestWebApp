package servlet;

import com.moviesearch.model.Director;
import com.moviesearch.service.DirectorService;
import com.moviesearch.servlet.AddDirectorServlet;
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

import static org.mockito.Mockito.*;

class AddDirectorServletTest {

    @InjectMocks
    private AddDirectorServlet servlet;

    @Mock
    private DirectorService directorService;

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

        String directorName = "Test Director";
        when(request.getParameter("name")).thenReturn(directorName);
        when(directorService.add(any(Director.class))).thenReturn(new Director(1, directorName));

        servlet.doPost(request, response);

        verify(directorService, times(1))
                .add(argThat(director -> director.getName().equals(directorName)));
        verify(response, times(1))
                .sendRedirect("director-added.jsp");
    }



    @Test
    void testDoGet() throws IOException {
        when(request.getRequestDispatcher("addDirector.jsp")).thenReturn(requestDispatcher);

        servlet.doGet(request, response);

        verify(response, times(1)).sendRedirect("addDirector.jsp");
    }
}