package com.jdc.flux.hello.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.flux.hello.model.dto.TownshipDto;
import com.jdc.flux.hello.model.entity.Township;
import com.jdc.flux.hello.model.repo.DivisionRepo;
import com.jdc.flux.hello.model.repo.TownshipRepo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TownshipService {

	private final TownshipRepo townshipRepo;
	private final DivisionRepo divisionRepo;
	
	public Flux<TownshipDto> search(Optional<Integer> division) {
		return division.map(d -> townshipRepo.findByDivisionId(d))
				.orElse(townshipRepo.findAll())
				.flatMap(this::decorate);
	}

	public Mono<TownshipDto> findById(int id) {
		return townshipRepo.findById(id).flatMap(this::decorate);
	}

	@Transactional
	public Mono<TownshipDto> save(Township data) {
		return townshipRepo.save(data).flatMap(this::decorate);
	}

	@Transactional
	public Mono<TownshipDto> update(int id, Township data) {
		return save(data.withId(id));
	}
	
	private Mono<TownshipDto> decorate(Township township) {
		return divisionRepo.findById(township.divisionId())
				.map(dto -> new TownshipDto(township.id(), township.name(), dto));
	}
}
