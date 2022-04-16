package project.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.model.StudyClass;
import project.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudyClassServletTest {

  @InjectMocks
  StudyClassServlet studyClassServlet;

  @Mock
  StudentService studentServiceMock;

  @Test
  void doGet_ShouldDirectToProperJsp_WhenCalled() throws ServletException, IOException {
    HttpServletRequest requestMock = mock(HttpServletRequest.class);
    HttpServletResponse responseMock = mock(HttpServletResponse.class);
    RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);

    when(requestMock.getRequestDispatcher("/views/student_class.jsp")).thenReturn(dispatcherMock);

    studyClassServlet.doGet(requestMock, responseMock);

    verify(dispatcherMock).forward(requestMock, responseMock);
  }

  @Test
  void doPost_ShouldWorkProperly_WhenCalled() throws ServletException, IOException {
    HttpServletRequest requestMock = mock(HttpServletRequest.class);
    HttpServletResponse responseMock = mock(HttpServletResponse.class);
    RequestDispatcher dispatcherMock = mock(RequestDispatcher.class);

    when(requestMock.getRequestDispatcher("/views/student_class.jsp")).thenReturn(dispatcherMock);
    when(requestMock.getParameter("student_id")).thenReturn("1L");
    when(studentServiceMock.getAllStudyClassesByStudentId("1L")).thenReturn(populateStudyClassList());

    studyClassServlet.doPost(requestMock, responseMock);

    verify(requestMock).setAttribute("classes", populateStudyClassList());
    verify(dispatcherMock).forward(requestMock, responseMock);
  }

  private static List<StudyClass> populateStudyClassList() {
    List<StudyClass> list = new ArrayList<>();
    list.add(new StudyClass(5L, "Math"));
    list.add(new StudyClass(10L, "History"));
    return list;
  }
}
