package com.burakozkan138.cinemabookingsystem.model;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "movies")
public class Movie extends BaseEntity {
  private String title;
  private String description;
  private String genre; // TODO: maybe create genre entity and reletion with movie
  private String director;
  @DBRef
  private ArrayList<Actor> cats = new ArrayList<>();
  private int duration;
  private String posterUrl = "default_poster.png";
}