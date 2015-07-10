package kr.dev.study.bbs.repository;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import kr.dev.study.bbs.domain.Bbs;
import kr.dev.study.config.MainConfig;
import kr.dev.study.config.TestConfig;
import kr.dev.study.repository.BbsRepository;
import kr.dev.study.test.AbstractMongoTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lordofthejars.nosqlunit.annotation.*;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainConfig.class})
@ActiveProfiles("test")
public class BbsRepositoryTest extends AbstractMongoTest {

	@Autowired BbsRepository bbsRepository;
	
	@Before
	public void setup() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	@UsingDataSet(locations = "/testData/bbs-data-2.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void findAll() {

		List<Bbs> result = bbsRepository.findAll();

		assertThat(result, hasSize(2));
		
//		for (Bbs bbs : result) {
//			System.out.println(bbs.toString());
//		}
	}
	
	@Test
	@ShouldMatchDataSet(location = "/testData/bbs-data-1.json")
	public void save() {
		// given 
		Bbs first = new Bbs("1","create test","내용","tester");

		bbsRepository.deleteAll();
		
		// when
		bbsRepository.save(first);

		// then
		List<Bbs> result = bbsRepository.findAll();
		assertThat(result, hasSize(1));
		
//		for (Bbs bbs : result) {
//			System.out.println(bbs.toString());
//		}
		

	}

}
