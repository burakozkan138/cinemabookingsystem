package com.burakozkan138.cinemabookingsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.burakozkan138.cinemabookingsystem.model.Actor;

public interface ActorRepository extends MongoRepository<Actor, String> {

}
