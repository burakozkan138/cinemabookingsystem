package com.burakozkan138.cinemabookingsystem.dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
  private String id;
  private String username;
  private String email;
  private String firstName;
  private String lastName;
}
