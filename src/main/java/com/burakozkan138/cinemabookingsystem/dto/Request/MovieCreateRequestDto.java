package com.burakozkan138.cinemabookingsystem.dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieCreateRequestDto {
  @NotNull(message = "The title field cannot be empty.")
  @Size(min = 2, max = 50, message = "The title field must be between 2 and 50 characters.")
  private String title;
  @NotNull(message = "The description field cannot be empty.")
  @Size(min = 2, max = 500, message = "The description field must be between 2 and 500 characters.")
  private String description;
  @NotNull(message = "The genre field cannot be empty.")
  @Size(min = 2, max = 50, message = "The genre field must be between 2 and 50 characters.")
  private String genre;
  @NotNull(message = "The director field cannot be empty.")
  @Size(min = 2, max = 50, message = "The director field must be between 2 and 50 characters.")
  private String director;
  @NotNull(message = "The duration field cannot be empty.")
  @Min(value = 1, message = "The duration field must be minumum 1 minute.")
  private int duration;
}