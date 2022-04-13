package project.service;

import java.util.List;
import project.dao.StudentDao;
import project.exception.WebAppException;
import project.model.Student;
import project.model.StudyClass;

public class StudentService {

  private StudentDao studentDao = StudentDao.getInstance();

  private StudentService() {
  }

  /**
   * This method returns the INSTANCE of the StudentService.
   *
   * @return
   */
  public static StudentService getInstance() {
    return ThreadSafeSingleton.INSTANCE;
  }

  /**
   * This method calls corresponding StudentDao method to add student record, and returns student id.
   *
   * @param firstName
   * @param lastName
   * @return
   */
  public Long addStudent(String firstName, String lastName) {
    Student student = new Student();
    student.setFirstName(firstName);
    student.setLastName(lastName);
    return studentDao.addStudent(student);
  }

  /**
   * This method converts id type from String to Long and calls corresponding StudentDao method to delete student record
   * with the provided id.
   *
   * @param id
   */
  public void deleteStudentById(String id) {
    try {
      Long parsedId = Long.valueOf(id);
      studentDao.deleteStudent(parsedId);
    } catch (NumberFormatException e) {
      throw new WebAppException("Id value is incorrect");
    }
  }

  /**
   * This method converts id type from String to Long and calls corresponding StudentDao method to get the list of
   * StudentClass objects related to the provided id.
   *
   * @param id
   * @return
   */
  public List<StudyClass> getAllStudyClassesByStudentId(String id) {
    try {
      Long parsedId = Long.valueOf(id);
      return studentDao.getAllClassesAndSaveLog(parsedId);
    } catch (NumberFormatException e) {
      throw new WebAppException("Id value is incorrect");
    }
  }

  private static class ThreadSafeSingleton {

    private static final StudentService INSTANCE = new StudentService();
  }
}
