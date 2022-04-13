package project.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.dao.StudentDao;
import project.exception.WebAppException;
import project.model.Student;
import project.model.StudyClass;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

  @InjectMocks
  StudentService studentService;

  @Mock
  StudentDao studentDaoMock;

  @Test
  void addStudent_ShouldReturnId_WhenValidArgumentsProvided() {
    when(studentDaoMock.addStudent(any(Student.class))).thenReturn(1L);

    studentService.addStudent("Ivan", "Petrov");

    verify(studentDaoMock).addStudent(any(Student.class));
  }

  @Test
  void getAllStudyClassesByStudentId_ShouldReturnList_WhenValidIdProvided() {
    when(studentDaoMock.getAllClassesAndSaveLog(1L)).thenReturn(populateStudyClassList());

    studentService.getAllStudyClassesByStudentId("1");

    verify(studentDaoMock).getAllClassesAndSaveLog(1L);
  }

  @Test
  void getAllStudyClassesByStudentId_ShouldThrowException_WhenInvalidIdTypeProvided() {
    Assertions.assertThrows(WebAppException.class, () -> studentService.getAllStudyClassesByStudentId("qwerty"));
  }

  @Test
  void deleteStudentById_ShouldDeleteStudent_WhenValidIdProvided() {
    doNothing().when(studentDaoMock).deleteStudent(1L);

    studentService.deleteStudentById("1");

    verify(studentDaoMock).deleteStudent(1L);
  }

  @Test
  void deleteStudentById_ShouldThrowException_WhenInvalidIdTypeProvided() {
    Assertions.assertThrows(WebAppException.class, () -> studentService.deleteStudentById("qwerty"));
  }

  private static List<StudyClass> populateStudyClassList() {
    List<StudyClass> list = new ArrayList<>();
    list.add(new StudyClass(5L, "Math"));
    list.add(new StudyClass(10L, "History"));
    return list;
  }
}
