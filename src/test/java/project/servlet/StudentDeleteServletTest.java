package project.servlet;

import static org.mockito.Mockito.doNothing;
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
public class StudentDeleteServletTest {

  @InjectMocks
  StudentDeleteServlet studentDeleteServlet;

  @Mock
  StudentService studentServiceMock;

  @Test
  void doGet_ShouldDirectToProperJsp_WhenCalled() throws ServletException, IOException {
    HttpServletRequest requestMock = mock(HttpServletRequest.class);
    HttpServletResponse responseMock = mock(HttpServletResponse.class);
    RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);

    when(requestMock.getRequestDispatcher("/views/student_delete.jsp")).thenReturn(dispatcherMock);

    studentDeleteServlet.doGet(requestMock, responseMock);

    verify(dispatcherMock).forward(requestMock, responseMock);
  }

  @Test
  void doPost_ShouldWorkProperly_WhenCalled() throws ServletException, IOException {
    HttpServletRequest requestMock = mock(HttpServletRequest.class);
    HttpServletResponse responseMock = mock(HttpServletResponse.class);
    RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);

    when(requestMock.getRequestDispatcher("/views/student_delete.jsp")).thenReturn(dispatcherMock);
    when(requestMock.getParameter("student_id")).thenReturn("1");
    doNothing().when(studentServiceMock).deleteStudentById("1");

    studentDeleteServlet.doPost(requestMock, responseMock);

    verify(requestMock).setAttribute("message", "Record was successfully deleted.");
    verify(dispatcherMock).forward(requestMock, responseMock);
  }
}
