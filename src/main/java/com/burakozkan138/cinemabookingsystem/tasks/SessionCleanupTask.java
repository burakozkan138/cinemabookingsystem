package com.burakozkan138.cinemabookingsystem.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.burakozkan138.cinemabookingsystem.model.Session;
import com.burakozkan138.cinemabookingsystem.repository.SessionRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class SessionCleanupTask {
  private final SessionRepository sessionRepository;

  @Scheduled(fixedRate = (60000 * 5)) // 5 minutes
  public void cleanupExpiredSessions() {
    System.out.println("Cleaning up expired sessions");
    List<Session> sessions = sessionRepository.deleteAllByDateandEndTimeBefore(LocalDate.now(), LocalTime.now());
    System.out.println("Deleted " + sessions.size() + " sessions");
  }
}
