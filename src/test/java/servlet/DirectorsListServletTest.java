package servlet;

import com.moviesearch.dao.DirectorDAO;
import com.moviesearch.model.Director;
import com.moviesearch.servlet.DirectorsListServlet;
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

public class DirectorsListServletTest {

    @InjectMocks
    private DirectorsListServlet servlet;

    @Mock
    private DirectorDAO directorDAO;

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
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("directors.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);

        verify(request).setAttribute("directors", directorDAO.getAll());
        verify(dispatcher).forward(request, response);}

    @Test
    void testDoPost() throws IOException, SQLException {
        when(request.getParameter("id")).thenReturn("1");
        List<Director> directors = Arrays.asList(new Director(1, "Director 1"));
        doReturn(directors).when(directorDAO).getAll();
        when(request.getRequestDispatcher("directors")).thenReturn(requestDispatcher);

        servlet.doPost(request, response);

        verify(directorDAO, times(1)).delete(1);
        verify(response, times(1)).sendRedirect("directors");
    }
}
