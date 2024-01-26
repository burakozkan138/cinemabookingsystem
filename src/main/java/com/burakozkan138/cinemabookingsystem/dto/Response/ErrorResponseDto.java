package com.burakozkan138.cinemabookingsystem.dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto<T> extends BaseResponseDto<T> {
  private T errors;

  public ErrorResponseDto(T errors, String message, boolean success, int status) {
    super(message, success, status);
    this.errors = errors;
  }
}
