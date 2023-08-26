package com.jdc.location.service;

import org.springframework.stereotype.Service;

import com.jdc.location.model.entity.Division;
import com.jdc.location.model.repo.DivisionRepo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DivisionService {
	
	private final DivisionRepo repo;

	public Flux<Division> getAll() {
		return repo.findAll();
	}

	public Mono<Division> save(Division division) {
		return repo.save(division);
	}

	public Mono<Division> findById(String id) {
		return repo.findById(id);
	}

	public Mono<Division> update(String id, Division division) {
		return repo.save(division.withId(id));
	}

}
