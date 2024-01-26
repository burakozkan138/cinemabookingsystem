package com.burakozkan138.cinemabookingsystem.dto.Response;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseDto<T> {
  private String message;
  private boolean success;
  private int status;
  private Optional<T> data;

  public BaseResponseDto(String message, boolean success, int status) {
    this.message = message;
    this.success = success;
    this.status = status;
  }

  public BaseResponseDto(T data, String message, boolean success, int status) {
    this.data = Optional.ofNullable(data);
    this.message = message;
    this.success = success;
    this.status = status;
  }
}
