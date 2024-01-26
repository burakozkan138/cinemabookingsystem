package com.burakozkan138.cinemabookingsystem.dto.Request;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorUpdateRequestDto {
  @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters.")
  private String firstname;
  @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters.")
  private String surname;
  @Past(message = "Birth date must be past.")
  private LocalDate birthDate;
  @Size(min = 2, max = 500, message = "Biography must be between 2 and 500 characters.")
  private String biography;
}
