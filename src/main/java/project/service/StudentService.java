package project.service;

import java.util.List;
import project.dao.StudentDao;
import project.model.StudyClass;

public class StudentService {

  private static final StudentService INSTANCE = new StudentService();
  private StudentDao studentDao = StudentDao.getInstance();

  private StudentService() {

  }

  public static StudentService getInstance() {
    return INSTANCE;
  }

  public Long addStudent(String firstName, String lastName) {
    return studentDao.addStudent(firstName, lastName);
  }

  public List<StudyClass> getAllStudyClassesByStudentId(String id) {
    return studentDao.getAllClassesAndSaveLog(Long.valueOf(id));
  }
}
