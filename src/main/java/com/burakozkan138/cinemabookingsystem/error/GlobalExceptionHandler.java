package com.burakozkan138.cinemabookingsystem.error;

import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.burakozkan138.cinemabookingsystem.dto.Response.ErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return new ErrorResponse<>(
        errors,
        "Validation Error",
        false,
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse<String> handleBadRequestExceptions(BadRequestException ex) {
    return new ErrorResponse<>(
        ex.getMessage(),
        "BAD_REQUEST",
        false,
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse<String> handleBadRequestExceptions(Exception ex) {
    ex.printStackTrace();
    return new ErrorResponse<>(
        ex.getMessage(),
        "INTERNAL_SERVER_ERROR",
        false,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
