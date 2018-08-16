package com.gqlauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages={"com.gqlauth"})
public class BasicMongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "BasicAuthDB";
	}
	
	@Override
	public MongoClient mongoClient() {
		return new MongoClient();
	}
}