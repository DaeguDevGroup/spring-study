package kr.dev.study.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@ContextConfiguration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableMongoRepositories(basePackages = "kr.dev.study.repository")
public class MongoConfig {
	
	@Profile("product")
	@Configuration
	static class ProductMongoConfig extends AbstractMongoConfiguration {
		 
		 @Value("${mongo.uri}")
		 private String uri;
		 
		 @Value("${mongo.db}")
		 private String databaseName;

		@Override
		protected String getDatabaseName() {
			return databaseName;
		}

		@Override
		public Mongo mongo() throws Exception {
			return new MongoClient(new MongoClientURI(uri));
		}
	}
	
	@Profile("test")
	@Configuration
	static class TestMongoConfig extends AbstractMongoConfiguration {
		 
		 @Value("${mongo.test.uri}")
		 private String uri;
		 
		 @Value("${mongo.test.db}")
		 private String databaseName;

		@Override
		protected String getDatabaseName() {
			return databaseName;
		}

		@Override
		public Mongo mongo() throws Exception {
			return new MongoClient(new MongoClientURI(uri));
		}
	}
	 
}
