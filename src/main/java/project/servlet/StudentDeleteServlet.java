package project.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.service.StudentService;

@WebServlet("/student_delete")
public class StudentDeleteServlet extends HttpServlet {

  private StudentService studentService = StudentService.getInstance();

  /**
   * This method allows a user to move to the student_delete.jsp page.
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/student_delete.jsp");
    requestDispatcher.forward(request, response);
  }

  /**
   * This method allows a user to delete student record by providing his ID in the request and returns message in the
   * response.
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/student_delete.jsp");
    studentService.deleteStudentById(request.getParameter("student_id"));
    request.setAttribute("message", "Record was successfully deleted.");
    requestDispatcher.forward(request, response);
  }
}
