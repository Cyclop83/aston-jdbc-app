package project.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.service.StudentService;

@WebServlet("/student_class")
public class StudyClassServlet extends HttpServlet {

  private StudentService studentService = StudentService.getInstance();

  /**
   * This method allows a user to move to the student_class.jsp page.
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/student_class.jsp");
    requestDispatcher.forward(request, response);
  }

  /**
   * This method allows a user to get a List of StudyClass objects, related to the given student ID. Request object
   * provides provides ID, response contains List<StudyClass></>
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/student_class.jsp");
    request.setAttribute("classes", studentService.getAllStudyClassesByStudentId(request.getParameter("student_id")));
    requestDispatcher.forward(request, response);
  }
}
