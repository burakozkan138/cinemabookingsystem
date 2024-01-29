package com.burakozkan138.cinemabookingsystem.model;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "actors")
public class Actor extends BaseEntity {
  private String firstname;
  private String surname;
  private LocalDate birthDate;
  private String biography;
  private String imageUrl = "default_actor.png";
  @DBRef
  private ArrayList<Movie> movies = new ArrayList<>();
}
