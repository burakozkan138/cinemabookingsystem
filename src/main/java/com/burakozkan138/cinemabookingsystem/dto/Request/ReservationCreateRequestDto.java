package com.burakozkan138.cinemabookingsystem.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationCreateRequestDto {
  @NotNull(message = "Session id cannot be null")
  private String sessionId;
  @NotNull(message = "Seat x cannot be null")
  @Positive(message = "Seat x must be positive")
  private int x;
  @NotNull(message = "Seat y cannot be null")
  @Positive(message = "Seat y must be positive")
  private int y;
}
