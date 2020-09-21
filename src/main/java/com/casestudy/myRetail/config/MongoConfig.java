package com.casestudy.myRetail.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@PropertySource("classpath:application.properties")
public class MongoConfig extends AbstractMongoClientConfiguration {
	@Autowired
	private Environment env;

	@Override
	protected String getDatabaseName() {
		return env.getProperty("spring.data.mongodb.database");
	}

	@Override
	public MongoClient mongoClient() {
		StringBuilder client = new StringBuilder();
		client.append("mongodb://").append(env.getProperty("spring.data.mongodb.host")).append(":")
				.append(env.getProperty("spring.data.mongodb.port")).append("/").append(getDatabaseName());
		ConnectionString connectionString = new ConnectionString(client.toString());
		MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.build();

		return MongoClients.create(mongoClientSettings);
	}

}
