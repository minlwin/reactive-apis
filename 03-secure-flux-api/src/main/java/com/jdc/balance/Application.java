package com.jdc.balance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.jdc.balance.model.repo.base.BaseRepositoryImpl;

@SpringBootApplication
@PropertySource("jwt.properties")
@EnableReactiveMongoRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
