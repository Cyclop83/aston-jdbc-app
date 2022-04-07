package project.model;

import java.util.Objects;

public class Student {

  private Long id;
  
  private String firstName;
  
  private String lastName;

  public Student() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Student)) {
      return false;
    }
    Student student = (Student) o;
    return Objects.equals(id, student.id) && Objects.equals(firstName, student.firstName)
        && Objects.equals(lastName, student.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }
}
