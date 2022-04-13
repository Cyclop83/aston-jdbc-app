package project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "classes")
public class StudyClass {

  @Id
  @SequenceGenerator(name = "classSequence", sequenceName = "classes_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classSequence")
  @Column(name = "class_id")
  private Long id;

  @Column(name = "class_name")
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
