package com.burakozkan138.cinemabookingsystem.dto.Response;

import java.util.Optional;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
  private String message;
  private boolean success;
  private HttpStatusCode status;
  private Optional<T> data;

  public BaseResponse(String message, boolean success, HttpStatusCode status) {
    this.status = status;
    this.message = message;
    this.success = success;
    this.data = Optional.empty();
  }

  public BaseResponse(T data, String message, boolean success, HttpStatusCode status) {
    this.data = Optional.of(data);
    this.message = message;
    this.success = success;
    this.status = status;
  }
}
