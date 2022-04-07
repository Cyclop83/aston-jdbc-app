package project.model;

import java.util.Objects;

public class StudyClass {

  private Long id;

  private String className;

  public StudyClass() {
  }

  public StudyClass(Long id, String className) {
    this.id = id;
    this.className = className;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StudyClass)) {
      return false;
    }
    StudyClass that = (StudyClass) o;
    return Objects.equals(id, that.id) && Objects.equals(className, that.className);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, className);
  }
}
