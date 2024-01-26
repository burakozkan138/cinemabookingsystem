package com.burakozkan138.cinemabookingsystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.burakozkan138.cinemabookingsystem.model.Reservation;
import com.burakozkan138.cinemabookingsystem.model.User;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
  List<Reservation> findByUser(User user);
}
