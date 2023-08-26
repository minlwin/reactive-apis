package com.jdc.flux.hello.api;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.flux.hello.model.dto.TownshipDto;
import com.jdc.flux.hello.model.entity.Township;
import com.jdc.flux.hello.service.TownshipService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("townships")
@RequiredArgsConstructor
public class TownshipApi {

	private final TownshipService service;
	
	@GetMapping
	Flux<TownshipDto> search(Optional<Integer> division) {
		return service.search(division);
	}
	
	@PostMapping
	Mono<TownshipDto> create(@RequestBody Township data) {
		return service.save(data);
	}
	
	@GetMapping("{id}")
	Mono<TownshipDto> findById(@PathVariable int id) {
		return service.findById(id);
	}

	@PutMapping("{id}")
	Mono<TownshipDto> update(@PathVariable int id, @RequestBody Township data) {
		return service.update(id, data);
	}
}
