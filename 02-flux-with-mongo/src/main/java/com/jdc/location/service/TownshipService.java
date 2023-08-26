package com.jdc.location.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jdc.location.model.dto.TownshipDto;
import com.jdc.location.model.entity.Township;
import com.jdc.location.model.repo.DivisionRepo;
import com.jdc.location.model.repo.TownshipRepo;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TownshipService {
	
	private final TownshipRepo townshipRepo;
	private final DivisionRepo divisionRepo;

	public Flux<TownshipDto> search(Optional<String> division) {
		return division.map(d -> townshipRepo.findByDivision(d))
				.orElse(townshipRepo.findAll()).flatMap(this::decorate);
	}

	public Mono<TownshipDto> save(Township data) {
		return townshipRepo.save(data).flatMap(this::decorate);
	}

	public Mono<TownshipDto> findById(String id) {
		return townshipRepo.findById(id).flatMap(this::decorate);
	}

	public Mono<TownshipDto> update(String id, Township data) {
		return save(data.withId(id));
	}

	private Mono<TownshipDto> decorate(Township data) {
		return divisionRepo.findById(data.id()).map(a -> new TownshipDto(data.id(), data.name(), a));
	}
}
