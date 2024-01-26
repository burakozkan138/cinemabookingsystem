package com.burakozkan138.cinemabookingsystem.dto.Request;

import com.burakozkan138.cinemabookingsystem.enums.ScreenType;
import com.burakozkan138.cinemabookingsystem.enums.SoundSystem;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HallUpdateRequestDto {
  @Size(min = 2, max = 50, message = "The name field must be between 2 and 50 characters.")
  private String name;
  private ScreenType screenType;
  private SoundSystem soundSystem;
}
