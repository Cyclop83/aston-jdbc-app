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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentServletTest {

  @InjectMocks
  StudentServlet studentServlet;

  @Mock
  StudentService studentServiceMock;

  @Test
  void doGet_ShouldDirectToProperJsp_WhenCalled() throws ServletException, IOException {
    HttpServletRequest requestMock = mock(HttpServletRequest.class);
    HttpServletResponse responseMock = mock(HttpServletResponse.class);
    RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);

    when(requestMock.getRequestDispatcher("/views/student.jsp")).thenReturn(dispatcherMock);

    studentServlet.doGet(requestMock, responseMock);

    verify(dispatcherMock).forward(requestMock, responseMock);
  }

  @Test
  void doPost_ShouldWorkProperly_WhenCalled() throws ServletException, IOException {
    HttpServletRequest requestMock = mock(HttpServletRequest.class);
    HttpServletResponse responseMock = mock(HttpServletResponse.class);
    RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);

    when(requestMock.getRequestDispatcher("/views/student.jsp")).thenReturn(dispatcherMock);
    when(requestMock.getParameter("first_name")).thenReturn("Ivan");
    when(requestMock.getParameter("last_name")).thenReturn("Petrov");
    when(studentServiceMock.addStudent("Ivan", "Petrov")).thenReturn(1L);

    studentServlet.doPost(requestMock, responseMock);

    verify(requestMock).setAttribute("id", 1L);
    verify(dispatcherMock).forward(requestMock, responseMock);
  }
}
