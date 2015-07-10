package kr.dev.study.bbs.controller;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.lordofthejars.nosqlunit.mongodb.MongoDbConfigurationBuilder.mongoDb;
import kr.dev.study.bbs.domain.Bbs;
import kr.dev.study.config.MainConfig;
import kr.dev.study.config.TestConfig;
import kr.dev.study.config.WebMvcConfig;
import kr.dev.study.repository.BbsRepository;
import kr.dev.study.test.AbstractMongoTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.lordofthejars.nosqlunit.annotation.ShouldMatchDataSet;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainConfig.class, WebMvcConfig.class})
@WebAppConfiguration
@ActiveProfiles("test")
public class BbsControllerTest extends AbstractMongoTest {
	
    private static final String ID = "1";
    private static final String ID_2 = "2";
    private static final String ID_3 = "3";
	
    @Autowired
    private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;

	@Autowired BbsRepository bbsRepository;
	
	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	@ShouldMatchDataSet(location = "/testData/bbs-data-1.json")
	public void submitSuccess() throws Exception {
		
		bbsRepository.deleteAll();
		
		this.mockMvc.perform(
			post("/bbs")
				.param("id", ID)
				.param("subject", "create test")
				.param("content", "내용")
				.param("writer", "tester")
		)
		.andExpect(status().isMovedTemporarily())
		.andExpect(redirectedUrl("/bbs"));
	}
	
	@Test
	public void submitErrorWhenEmptyWriter() throws Exception {
		this.mockMvc.perform(
			post("/bbs")
				.param("subject", "create test3")
				.param("content", "내용3")
		)
		.andExpect(status().isOk())
		.andExpect(view().name("bbs/form"))
		.andExpect(model().errorCount(1))
		.andExpect(model().attributeHasFieldErrors("bbs", "writer"));
	}
	
	@Test
	@UsingDataSet(locations = "/testData/bbs-data-1.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void findById() throws Exception {
		
		this.mockMvc.perform(
			get("/bbs/" + ID)
		)
		.andExpect(status().isOk())
		.andExpect(view().name("bbs/view"))
		.andExpect(model().attribute("item", notNullValue()))
		.andExpect(model().attribute("item", hasProperty("id", is(ID))))
		.andExpect(model().attribute("item", hasProperty("subject", is("create test"))))
		.andExpect(model().attribute("item", hasProperty("content", is("내용"))))
		.andExpect(model().attribute("item", hasProperty("writer", is("tester"))))
		.andExpect(model().size(1));
	}
	
	@Test
	@UsingDataSet(locations = "/testData/bbs-data-2.json", loadStrategy = LoadStrategyEnum.CLEAN_INSERT)
	public void findAll() throws Exception {
		
		this.mockMvc.perform(
			get("/bbs")
		)
		.andExpect(status().isOk())
		.andExpect(view().name("bbs/list"))
        .andExpect(model().attribute("items", hasSize(2)))
        .andExpect(model().attribute("items", hasItem(
                allOf(
                        hasProperty("id", is(ID_2)),
                        hasProperty("subject", is("create test2")),
                        hasProperty("content", is("내용2")),
                        hasProperty("writer", is("tester"))
                )
        )))
        .andExpect(model().attribute("items", hasItem(
                allOf(
                        hasProperty("id", is(ID_3)),
                        hasProperty("subject", is("create test3")),
                        hasProperty("content", is("내용3")),
                        hasProperty("writer", is("tester"))
                )
        )));
	}

}
