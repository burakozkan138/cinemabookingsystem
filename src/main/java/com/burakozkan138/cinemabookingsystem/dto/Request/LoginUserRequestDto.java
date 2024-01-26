package com.burakozkan138.cinemabookingsystem.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequestDto {
  @NotNull(message = "Username cannot be null")
  @Size(min = 3, max = 16, message = "Username must be between 3 and 16 characters")
  private String username;
  @NotNull(message = "Password cannot be null")
  @Size(min = 6, max = 256, message = "Password must be between 6 and 256 characters")
  private String password;
}
