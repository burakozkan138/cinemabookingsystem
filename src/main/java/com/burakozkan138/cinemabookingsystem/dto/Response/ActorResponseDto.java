package com.burakozkan138.cinemabookingsystem.dto.Response;

import java.time.LocalDate;
import java.util.List;

import com.burakozkan138.cinemabookingsystem.model.Movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorResponseDto {
  private String id;
  private String firstname;
  private String surname;
  private LocalDate birthDate;
  private String biography;
  private String imageUrl;
  private List<Movie> movies;
}
