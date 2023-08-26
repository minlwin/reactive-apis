package com.jdc.flux.hello.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.flux.hello.model.entity.Division;
import com.jdc.flux.hello.model.repo.DivisionRepo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DivisionService {

	private final DivisionRepo repo;
	
	@Transactional
	public Mono<Division> save(Division data) {
		return repo.save(data);
	}
	
	public Flux<Division> getAll() {
		return repo.findAll();
	}

	public Mono<Division> findById(int id) {
		return repo.findById(id);
	}

	@Transactional
	public Mono<Division> update(int id, Division division) {
		return save(division.withId(id));
	}
}
