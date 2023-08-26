package com.jdc.flux.hello.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.flux.hello.model.entity.Division;
import com.jdc.flux.hello.service.DivisionService;

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
	Mono<Division> findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PutMapping("{id}")
	Mono<Division> update(@PathVariable int id, @Validated @RequestBody Division division) {
		return service.update(id, division);
	}
	
}
