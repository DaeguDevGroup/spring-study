package kr.dev.study.test;

import static com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb.InMemoryMongoRuleBuilder.newInMemoryMongoDbRule;
import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;

import java.util.Set;

import kr.dev.study.bbs.service.impl.RepositoryBbsService;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.lordofthejars.nosqlunit.mongodb.InMemoryMongoDb;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class AbstractMongoTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractMongoTest.class);

//	@ClassRule
//	public static InMemoryMongoDb inMemoryMongoDb = newInMemoryMongoDbRule().build();
	@Rule
	public MongoDbRule mongoDbRule = newMongoDbRule().defaultManagedMongoDb("test");
	
	public AbstractMongoTest() {
		super();
	}

}