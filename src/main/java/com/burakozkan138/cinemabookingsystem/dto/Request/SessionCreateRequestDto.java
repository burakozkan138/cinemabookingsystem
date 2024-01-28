package com.burakozkan138.cinemabookingsystem.dto.Request;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionCreateRequestDto {
  @NotNull(message = "Date cannot be null")
  @FutureOrPresent(message = "Date cannot be in the past")
  private LocalDate date;
  @NotNull(message = "Time cannot be null")
  private List<LocalTime> times;
  @NotNull(message = "Price cannot be null")
  @Positive(message = "Price must be positive")
  private double price;
  @NotNull(message = "Movie id cannot be null")
  private String movieId;
  @NotNull(message = "Hall id cannot be null")
  private String hallId;
}
