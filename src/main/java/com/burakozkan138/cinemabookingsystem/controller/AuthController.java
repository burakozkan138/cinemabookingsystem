package com.burakozkan138.cinemabookingsystem.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burakozkan138.cinemabookingsystem.dto.Request.LoginUserRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.RegisterUserRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.AuthResponseDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.BaseResponse;
import com.burakozkan138.cinemabookingsystem.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
  private final UserService userService;

  @PostMapping("/login")
  public BaseResponse<AuthResponseDto> login(
      @Valid @RequestBody LoginUserRequestDto loginUserRequestDto) throws BadRequestException {
    AuthResponseDto response = userService.login(loginUserRequestDto);
    return new BaseResponse<>(response, "Login successful", true, HttpStatus.OK);
  }

  @PostMapping("/register")
  public BaseResponse<AuthResponseDto> register(
      @Valid @RequestBody RegisterUserRequestDto registerUserRequestDto) throws BadRequestException {
    AuthResponseDto response = userService.register(registerUserRequestDto);
    return new BaseResponse<>(response, "Register successful", true, HttpStatus.OK);
  }
}
