package com.udacity.course3.reviews;

import com.mongodb.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public class ReviewsApplicationTests
{

	public static void main(String[] args) {
		SpringApplication.run(ReviewsApplication.class, args);
	}


	@Bean
	public MongoClient m() throws Exception {
		return new MongoClient("localhost");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(m(), "jdnd-c3");
	}

}