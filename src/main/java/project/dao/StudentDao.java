package project.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import project.exception.WebAppException;
import project.model.RequestLog;
import project.model.Student;
import project.model.StudyClass;
import project.util.HibernateUtil;

public class StudentDao {

  private static final String DELETE_STUDENT = "DELETE " + Student.class.getName() + " WHERE id = :ID";

  private StudentDao() {
  }

  /**
   * This method returns the INSTANCE object of StudentDao class.
   *
   * @return
   */
  public static StudentDao getInstance() {
    return ThreadSafeSingleton.INSTANCE;
  }

  /**
   * This method saves the given Student object into database.
   *
   * @param student
   * @return
   */
  public Long addStudent(Student student) {
    Transaction transaction = null;

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.persist(student);
      session.flush();
      Long id = student.getId();
      transaction.commit();
      return id;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new WebAppException("Exception during Student saving");
    }
  }

  /**
   * This method deletes record with the provided id from the student table.
   *
   * @param id
   */
  public void deleteStudent(Long id) {
    Transaction transaction = null;

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Query query = session.createQuery(DELETE_STUDENT).setParameter("ID", id);
      query.executeUpdate();
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
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
    Transaction transaction = null;

    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Student student = session.get(Student.class, id);
      session.persist(new RequestLog(id, LocalDateTime.now()));
      session.flush();
      transaction.commit();
      return new ArrayList<>(student.getClasses());
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
      throw new WebAppException("StudyClass list has not been retrieved.");
    }
  }

  private static class ThreadSafeSingleton {

    private static final StudentDao INSTANCE = new StudentDao();
  }
}
