package project.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IndexServletTest {

  @InjectMocks
  IndexServlet indexServlet;

  @Test
  void doGet_ShouldDirectToProperJsp_WhenCalled() throws ServletException, IOException {
    HttpServletRequest requestMock = mock(HttpServletRequest.class);
    HttpServletResponse responseMock = mock(HttpServletResponse.class);
    RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);

    when(requestMock.getRequestDispatcher("/views/index.jsp")).thenReturn(dispatcherMock);

    indexServlet.doGet(requestMock, responseMock);

    verify(dispatcherMock).forward(requestMock, responseMock);
  }
}
