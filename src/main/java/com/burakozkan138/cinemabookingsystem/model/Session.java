package com.burakozkan138.cinemabookingsystem.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "sessions")
public class Session extends BaseEntity {
  private LocalDate date;
  private LocalTime startTime;
  private LocalTime endTime;
  @DBRef
  private Movie movie;
  @DBRef
  private Hall hall;
  private double price;
  private int maxCapacity;
  private ArrayList<Point> bookedSeats = new ArrayList<>();
  @DBRef
  private ArrayList<Reservation> reservations;
}
