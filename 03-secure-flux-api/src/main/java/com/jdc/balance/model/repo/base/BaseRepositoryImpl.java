package com.jdc.balance.model.repo.base;

import java.io.Serializable;

import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleReactiveMongoRepository;

import reactor.core.publisher.Flux;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleReactiveMongoRepository<T, ID> 
	implements BaseRepository<T, ID>{
	
	
	private MongoEntityInformation<T, ID> entityInformation;
	private ReactiveMongoOperations mongoOperations;
	
	public BaseRepositoryImpl(MongoEntityInformation<T, ID> entityInformation,
			ReactiveMongoOperations mongoOperations) {
		super(entityInformation, mongoOperations);
		
		this.entityInformation = entityInformation;
		this.mongoOperations = mongoOperations;
	}

	@Override
	public Flux<T> select(Query query) {
		return mongoOperations.find(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
	}


}
