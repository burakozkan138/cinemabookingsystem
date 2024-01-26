package com.burakozkan138.cinemabookingsystem.enums;

public enum SoundSystem {
  STANDARD("Standard"),
  DOLBY_DIGITAL("Dolby Digital"),
  DOLBY_DIGITAL_PLUS("Dolby Digital Plus"),
  DOLBY_ATMOS("Dolby Atmos"),
  DTS("DTS"),
  DTS_HD("DTS-HD"),
  SDDS("SDDS");

  private final String displayName;

  SoundSystem(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  @Override
  public String toString() {
    return this.displayName;
  }
}
