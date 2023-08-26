package com.jdc.location.model.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.jdc.location.model.entity.Division;

public interface DivisionRepo extends ReactiveMongoRepository<Division, String> {

}
