package com.burakozkan138.cinemabookingsystem.dto.Response;

import com.burakozkan138.cinemabookingsystem.model.Point;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationResponseDto {
  private String id;
  private UserResponseDto user;
  private SessionResponseDto session;
  private String seat;
  private double totalPrice;
  private String paymentStatus;

  public void setSeat(Point seat) {
    this.seat = String.valueOf((int) seat.getX()) + (char) ((char) seat.getY() + 64); // Char start with 64 because y
                                                                                      // get minumum 1
  }
}