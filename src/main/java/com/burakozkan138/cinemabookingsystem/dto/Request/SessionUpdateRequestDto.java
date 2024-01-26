package com.burakozkan138.cinemabookingsystem.dto.Request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionUpdateRequestDto {
  @FutureOrPresent(message = "Date cannot be in the past")
  private LocalDate date;
  private LocalTime time;
  @Positive(message = "Price must be positive")
  private double price;
  private String movieId;
  private String hallId;
}
