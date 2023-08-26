package com.jdc.location.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.location.model.entity.Division;
import com.jdc.location.service.DivisionService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("divisions")
@RequiredArgsConstructor
public class DivisionApi {
	
	private final DivisionService service;

	@GetMapping
	Flux<Division> findAll() {
		return service.getAll();
	}
	
	@PostMapping
	Mono<Division> create(@Validated @RequestBody Division division) {
		return service.save(division);
	}
	
	@GetMapping("{id}")
	Mono<Division> findById(@PathVariable String id) {
		return service.findById(id);
	}
	
	@PutMapping("{id}")
	Mono<Division> update(@PathVariable String id, @Validated @RequestBody Division division) {
		return service.update(id, division);
	}
	
}