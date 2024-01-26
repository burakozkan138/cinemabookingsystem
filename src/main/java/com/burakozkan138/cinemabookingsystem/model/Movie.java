package com.burakozkan138.cinemabookingsystem.model;

import java.util.Set;

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
  private Set<Actor> cats;
  private int duration;
  private String posterUrl = "default_poster.png";
}