package project.model;

import java.time.LocalDateTime;

public class RequestLog {

  private Long requestId;

  private Long stidentId;

  private LocalDateTime requestTime;

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
