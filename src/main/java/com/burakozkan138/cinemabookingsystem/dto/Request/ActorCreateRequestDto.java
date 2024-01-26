package com.burakozkan138.cinemabookingsystem.dto.Request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorCreateRequestDto {
  @NotNull(message = "Firstname could not be null.")
  @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters.")
  private String firstname;
  @NotNull(message = "Surname could not be null.")
  @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters.")
  private String surname;
  @NotNull(message = "Birth date could not be null.")
  @Past(message = "Birth date must be past.")
  private LocalDate birthDate;
  @NotNull(message = "Biography could not be null.")
  @Size(min = 2, max = 500, message = "Biography must be between 2 and 500 characters.")
  private String biography;
}
