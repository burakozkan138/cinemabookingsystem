package com.burakozkan138.cinemabookingsystem.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseDto {
  private String accessToken;
  private String refreshToken;
  private UserResponseDto user;
}
