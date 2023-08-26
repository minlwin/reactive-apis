package com.jdc.location.api;

import java.util.Optional;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.location.model.dto.TownshipDto;
import com.jdc.location.model.entity.Township;
import com.jdc.location.service.TownshipService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("townships")
@RequiredArgsConstructor
public class TownshipApi {

	private final TownshipService service;
	
	@GetMapping
	Flux<TownshipDto> search(Optional<String> division) {
		return service.search(division);
	}
	
	@PostMapping
	Mono<TownshipDto> create(@Validated @RequestBody Township data) {
		return service.save(data);
	}
	
	@GetMapping("{id}")
	Mono<TownshipDto> findById(@PathVariable String id) {
		return service.findById(id);
	}

	@PutMapping("{id}")
	Mono<TownshipDto> update(@PathVariable String id, @Validated @RequestBody Township data) {
		return service.update(id, data);
	}
}