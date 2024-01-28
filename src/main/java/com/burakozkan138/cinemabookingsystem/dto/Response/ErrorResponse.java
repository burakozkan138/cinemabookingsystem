package com.burakozkan138.cinemabookingsystem.dto.Response;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse<T> extends BaseResponse<T> {
  private T errors;

  public ErrorResponse(T errors, String message, boolean success, HttpStatusCode status) {
    super(message, success, status);
    this.errors = errors;
  }
}
