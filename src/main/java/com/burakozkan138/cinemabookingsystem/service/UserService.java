package com.burakozkan138.cinemabookingsystem.service;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.burakozkan138.cinemabookingsystem.dto.Request.LoginUserRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Request.RegisterUserRequestDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.AuthResponseDto;
import com.burakozkan138.cinemabookingsystem.dto.Response.UserResponseDto;
import com.burakozkan138.cinemabookingsystem.model.User;
import com.burakozkan138.cinemabookingsystem.repository.UserRepository;
import com.burakozkan138.cinemabookingsystem.utils.JWTUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final JWTUtils jwtUtils;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
  }

  public AuthResponseDto login(LoginUserRequestDto loginUserRequestDto) throws BadRequestException {
    User user = userRepository.findByUsername(loginUserRequestDto.getUsername())
        .orElseThrow(
            () -> new UsernameNotFoundException("User not found with username: " + loginUserRequestDto.getUsername()));

    if (!passwordEncoder.matches(loginUserRequestDto.getPassword(), user.getPassword())) {
      throw new BadRequestException("Wrong password");
    }

    String accessToken = jwtUtils.genereateToken(user);
    String refreshToken = jwtUtils.generateRefreshToken(user);

    UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);

    return new AuthResponseDto(accessToken, refreshToken, userResponseDto);
  }

  public AuthResponseDto register(RegisterUserRequestDto registerUserRequestDto)
      throws BadRequestException {
    if (userRepository.existsByUsername(registerUserRequestDto.getUsername())) {
      throw new BadRequestException("User already exists with username: " + registerUserRequestDto.getUsername());
    }

    if (userRepository.existsByEmail(registerUserRequestDto.getEmail())) {
      throw new BadRequestException("User already exists with email: " + registerUserRequestDto.getEmail());
    }

    User mappedUser = modelMapper.map(registerUserRequestDto, User.class);
    if (mappedUser == null) { // maybe this is not necessary
      throw new BadRequestException("User can not be null");
    }
    mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
    User createdUser = userRepository.save(mappedUser);
    String accessToken = jwtUtils.genereateToken(createdUser);
    String refreshToken = jwtUtils.generateRefreshToken(createdUser);

    UserResponseDto userResponseDto = modelMapper.map(createdUser, UserResponseDto.class);

    return new AuthResponseDto(accessToken, refreshToken, userResponseDto);
  }
}
