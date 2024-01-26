package com.burakozkan138.cinemabookingsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.burakozkan138.cinemabookingsystem.model.Session;

public interface SessionRepository extends MongoRepository<Session, String> {

}
