package com.burakozkan138.cinemabookingsystem.enums;

public enum ScreenType {
  STANDARD("Standard"),
  IMAX("IMAX"),
  _3D("3D"),
  VIP("VIP");

  private final String displayName;

  ScreenType(String displayName) {
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
