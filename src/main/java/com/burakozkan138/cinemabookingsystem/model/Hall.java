package com.burakozkan138.cinemabookingsystem.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.burakozkan138.cinemabookingsystem.enums.ScreenType;
import com.burakozkan138.cinemabookingsystem.enums.SoundSystem;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "halls")
public class Hall extends BaseEntity {
  private String name;
  private int maxRow;
  private int maxColumn;
  private ScreenType screenType;
  private SoundSystem soundSystem;
}
