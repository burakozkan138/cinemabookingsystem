package com.burakozkan138.cinemabookingsystem.error;

import java.util.HashMap;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.burakozkan138.cinemabookingsystem.dto.Response.ErrorResponseDto;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponseDto<Map<String, String>>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    var response = new ErrorResponseDto<Map<String, String>>(
        errors,
        "Validation Error",
        false,
        HttpStatus.BAD_REQUEST.value());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponseDto<String>> handleBadRequestExceptions(BadRequestException ex) {
    var response = new ErrorResponseDto<String>(
        ex.getMessage(),
        "Bad Request",
        false,
        HttpStatus.BAD_REQUEST.value());

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorResponseDto<String>> handleExceptions(Exception ex) {
    var response = new ErrorResponseDto<String>(
        ex.getMessage(),
        "Forbidden",
        false,
        HttpStatus.INTERNAL_SERVER_ERROR.value());

    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
