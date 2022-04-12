package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import project.exception.WebAppException;
import project.model.StudyClass;
import project.util.HikariCPDataSource;

public class StudentDao {

  private static final StudentDao INSTANCE = new StudentDao();

  private static final String ADD_STUDENT = "INSERT INTO student (student_id, first_name, last_name) "
      + "VALUES (nextval('student_seq'), ?, ?)";

  private static final String DELETE_STUDENT = "DELETE FROM student WHERE student_id = ?";

  private static final String GET_ALL_CLASSES = "SELECT classes.class_id, classes.class_name FROM student "
      + "JOIN student_classes ON student.student_id = student_classes.student_id "
      + "JOIN classes ON student_classes.class_id = classes.class_id "
      + "WHERE student.student_id = ?";

  private static final String SAVE_REQUEST_LOG = "INSERT INTO request_log (request_id, student_id, request_time) "
      + "VALUES (nextval('request_seq'), ?, ?)";

  private StudentDao() {
  }

  /**
   * This method returns the INSTANCE object of StudentDao class.
   *
   * @return
   */
  public static StudentDao getInstance() {
    return INSTANCE;
  }

  /**
   * This method adds Student with the given firstName and lastName to the student table and returns ID of the added
   * record.
   *
   * @param firstName
   * @param lastName
   * @return
   */
  public Long addStudent(String firstName, String lastName) {
    try (Connection connection = HikariCPDataSource.getConnection()) {
      connection.setAutoCommit(false);
      connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
      PreparedStatement preparedStatement = connection.prepareStatement(ADD_STUDENT, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, firstName);
      preparedStatement.setString(2, lastName);
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      Long id = null;
      if (resultSet.next()) {
        id = resultSet.getLong(1);
      }
      connection.commit();
      return id;
    } catch (SQLException e) {
      throw new WebAppException("Student info has not been added.");
    }
  }

  /**
   * This method deletes record with the provided id from the student table.
   *
   * @param id
   */
  public void deleteStudent(Long id) {
    try (Connection connection = HikariCPDataSource.getConnection()) {
      connection.setAutoCommit(false);
      connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
      PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT);
      preparedStatement.setObject(1, id);
      preparedStatement.executeUpdate();
      connection.commit();
    } catch (SQLException e) {
      throw new WebAppException("Student deletion wasn't finished.");
    }
  }

  /**
   * This method returns list of StudyClass objects, related to the student with the provided id. It also adds
   * information about this request to the request_log table in the same transaction.
   *
   * @param id
   * @return
   */
  public List<StudyClass> getAllClassesAndSaveLog(Long id) {
    try (Connection connection = HikariCPDataSource.getConnection()) {
      connection.setAutoCommit(false);
      connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
      PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLASSES);
      preparedStatement.setObject(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      List<StudyClass> list = new ArrayList<>();
      while (resultSet.next()) {
        list.add(getStudyClassFromResultSet(resultSet));
      }
      PreparedStatement requestPreparedStatement = connection.prepareStatement(SAVE_REQUEST_LOG);
      requestPreparedStatement.setObject(1, id);
      requestPreparedStatement.setObject(2, LocalDateTime.now());
      requestPreparedStatement.executeUpdate();
      connection.commit();
      return list;
    } catch (SQLException e) {
      throw new WebAppException("StudyClasses info has not been retrieved.");
    }
  }

  private StudyClass getStudyClassFromResultSet(ResultSet resultSet) throws SQLException {
    StudyClass studyClass = new StudyClass();
    studyClass.setId(resultSet.getLong("class_id"));
    studyClass.setClassName(resultSet.getString("class_name"));
    return studyClass;
  }
}
