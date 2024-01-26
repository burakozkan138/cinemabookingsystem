package com.burakozkan138.cinemabookingsystem.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document(collection = "reservations")
@Data
@EqualsAndHashCode(callSuper = false)
public class Reservation extends BaseEntity {
  @DBRef
  private User user;
  @DBRef
  private Session session;
  private Point seat;
  private double totalPrice;
  private String paymentStatus = "PENDING";

  public void setSeat(int x, int y) {
    this.seat = new Point(x, y);
  }
}