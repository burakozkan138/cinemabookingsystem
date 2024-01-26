package com.burakozkan138.cinemabookingsystem.dto.Response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieResponseDto {
  private String id;
  private String title;
  private String description;
  private String genre;
  private String director;
  private int duration;
  private String posterUrl;
  private LocalDateTime createdAt;
}
