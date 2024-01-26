package com.burakozkan138.cinemabookingsystem.dto.Request;

import com.burakozkan138.cinemabookingsystem.enums.ScreenType;
import com.burakozkan138.cinemabookingsystem.enums.SoundSystem;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HallCreateRequestDto {
  @NotNull(message = "The name field cannot be empty.")
  @Size(min = 2, max = 50, message = "The name field must be between 2 and 50 characters.")
  private String name;
  @NotNull(message = "The maxRow field cannot be empty.")
  @Min(value = 1, message = "The maxRow field must be minumum 1.")
  @Max(value = 26, message = "The maxRow field must be maximum 26.") // 26 letters in alphabet
  private int maxRow;
  @NotNull(message = "The maxColumn field cannot be empty.")
  @Min(value = 1, message = "The maxColumn field must be minumum 1.")
  @Max(value = 100, message = "The maxColumn field must be maximum 100.")
  private int maxColumn;
  @NotNull(message = "The screenType field cannot be empty.")
  private ScreenType screenType;
  @NotNull(message = "The soundSystem field cannot be empty.")
  private SoundSystem soundSystem;
}
