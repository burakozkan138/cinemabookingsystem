package com.burakozkan138.cinemabookingsystem.dto.Response;

import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseDto<T> extends ResponseEntity<BaseResponseDto<T>> {
  private String message;
  private boolean success;
  private HttpStatusCode status;
  private Optional<T> data;

  public BaseResponseDto(String message, boolean success, HttpStatusCode status) {
    super(null, null, status);
    this.message = message;
    this.success = success;
    this.status = status;
  }

  public BaseResponseDto(T data, String message, boolean success, HttpStatusCode status) {
    super(null, null, status);
    this.data = Optional.ofNullable(data);
    this.message = message;
    this.success = success;
    this.status = status;
  }
}
