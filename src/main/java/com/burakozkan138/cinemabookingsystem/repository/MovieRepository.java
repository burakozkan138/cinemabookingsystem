package com.burakozkan138.cinemabookingsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.burakozkan138.cinemabookingsystem.model.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
}
