package com.jdc.location.model.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.jdc.location.model.entity.Township;

import reactor.core.publisher.Flux;

public interface TownshipRepo extends ReactiveMongoRepository<Township, String>{

	Flux<Township> findByDivision(String division);

}
