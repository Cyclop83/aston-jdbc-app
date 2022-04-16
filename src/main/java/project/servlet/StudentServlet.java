package project.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.service.StudentService;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

  private StudentService studentService = StudentService.getInstance();

  /**
   * This method allows a user to move to the student.jsp page.
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/student.jsp");
    requestDispatcher.forward(request, response);
  }

  /**
   * This method allows a user to add student to the student table by providing first name and last name in the request,
   * and returns registered student id in response object.
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/student.jsp");
    Long id = studentService.addStudent(request.getParameter("first_name"), request.getParameter("last_name"));
    request.setAttribute("id", id);
    requestDispatcher.forward(request, response);
  }
}
