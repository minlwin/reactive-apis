package com.jdc.flux.hello.model.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.jdc.flux.hello.model.entity.Division;

public interface DivisionRepo extends ReactiveCrudRepository<Division, Integer>{

}
