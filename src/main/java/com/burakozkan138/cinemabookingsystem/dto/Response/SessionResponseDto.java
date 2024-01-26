package com.burakozkan138.cinemabookingsystem.dto.Response;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionResponseDto {
  private String id;
  private LocalDate date;
  private LocalTime time;
  private int maxCapacity;
  private MovieResponseDto movie;
  private HallResponseDto hall;
  private double price;
}
