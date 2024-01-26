package com.burakozkan138.cinemabookingsystem.dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class MovieUpdateRequestDto {
  @Size(min = 2, max = 50, message = "The title field must be between 2 and 50 characters.")
  private String title;
  @Size(min = 2, max = 500, message = "The description field must be between 2 and 500 characters.")
  private String description;
  @Size(min = 2, max = 50, message = "The genre field must be between 2 and 50 characters.")
  private String genre;
  @Size(min = 2, max = 50, message = "The director field must be between 2 and 50 characters.")
  private String director;
  @Min(value = 1, message = "The duration field must be minumum 1 minute.",groups = {})
  private int duration;
}
