package com.burakozkan138.cinemabookingsystem.utils;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

@Component
public class TimeUtils {
  public boolean isTimeBetweenTwoTimes(LocalTime initialTime, LocalTime finalTime, LocalTime currentTime) {
    return currentTime.isAfter(initialTime) && currentTime.isBefore(finalTime);
  }

  public boolean isTimeDivisibleByFive(LocalTime time) {
    return time.getMinute() % 5 == 0;
  }

  public LocalTime roundTimeToNextFive(LocalTime time) {
    int minute = time.getMinute();
    if (minute % 5 == 0) {
      return time;
    }
    return time.plusMinutes(5 - minute % 5);
  }

  public LocalTime calculateEndTime(LocalTime startTime, int duration) {
    return this.roundTimeToNextFive(startTime.plusMinutes(duration));
  }
}
