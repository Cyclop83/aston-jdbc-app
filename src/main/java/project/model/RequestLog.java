package project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "request_log")
public class RequestLog {

  @Id
  @SequenceGenerator(name = "requestSequence", sequenceName = "request_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requestSequence")
  @Column(name = "request_id")
  private Long requestId;

  @Column(name = "student_id")
  private Long stidentId;

  @Column(name = "request_time")
  private LocalDateTime requestTime;

  public RequestLog() {
  }

  public RequestLog(Long stidentId, LocalDateTime requestTime) {
    this.stidentId = stidentId;
    this.requestTime = requestTime;
  }

  public Long getRequestId() {
    return requestId;
  }

  public void setRequestId(Long requestId) {
    this.requestId = requestId;
  }

  public Long getStidentId() {
    return stidentId;
  }

  public void setStidentId(Long stidentId) {
    this.stidentId = stidentId;
  }

  public LocalDateTime getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(LocalDateTime requestTime) {
    this.requestTime = requestTime;
  }
}
