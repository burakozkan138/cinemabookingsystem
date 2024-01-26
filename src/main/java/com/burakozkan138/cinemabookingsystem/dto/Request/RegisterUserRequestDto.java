package com.burakozkan138.cinemabookingsystem.dto.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequestDto {
  @NotNull(message = "Username cannot be null")
  @Size(min = 3, max = 16, message = "Username must be between 3 and 16 characters")
  private String username;
  @NotNull(message = "Password cannot be null")
  @Size(min = 6, max = 256, message = "Password must be between 6 and 256 characters")
  private String password;
  @NotNull(message = "Email cannot be null")
  @Email(message = "Email must be valid")
  private String email;
  @NotNull(message = "First name cannot be null")
  @Size(min = 2, max = 32, message = "First name must be between 2 and 32 characters")
  private String firstName;
  @NotNull(message = "Last name cannot be null")
  @Size(min = 2, max = 32, message = "Last name must be between 2 and 32 characters")
  private String lastName;
}
