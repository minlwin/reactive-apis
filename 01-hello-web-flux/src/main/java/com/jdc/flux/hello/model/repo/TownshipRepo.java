package com.jdc.flux.hello.model.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.jdc.flux.hello.model.entity.Township;

import reactor.core.publisher.Flux;

public interface TownshipRepo extends ReactiveCrudRepository<Township, Integer>{

	Flux<Township> findByDivisionId(Integer d);

}
