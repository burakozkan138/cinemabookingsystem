package com.burakozkan138.cinemabookingsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.burakozkan138.cinemabookingsystem.model.Hall;

@Repository
public interface HallRepository extends MongoRepository<Hall, String> {

}
