package com.burakozkan138.cinemabookingsystem.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.burakozkan138.cinemabookingsystem.model.Session;

public interface SessionRepository extends MongoRepository<Session, String> {
  @Query("{'date': {$eq: ?0}, 'startTime': {$gte: ?1, $lte: ?2}, 'hall.id': ?3}")
  List<Session> findByTimestampBetween(LocalDate date, LocalTime startTime, LocalTime endTime, String hallId);
}
