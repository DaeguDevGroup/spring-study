package kr.dev.study.bbs.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import kr.dev.study.bbs.domain.Bbs;
import kr.dev.study.bbs.service.BbsService;
import kr.dev.study.config.MainConfig;
import kr.dev.study.config.PersistConfig;
import kr.dev.study.config.TestConfig;
import kr.dev.study.config.WebMvcConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class WebAppContextBbsControllerTest {
	
    private static final String ID = "1";
	
    @Autowired
	private BbsService bbsServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() throws Exception {
		
		Mockito.reset(bbsServiceMock);
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        String[] beanDefinitionNames = wac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
        	if( beanDefinitionName.endsWith("Service") || beanDefinitionName.endsWith("Controller") ){
        		System.out.println(beanDefinitionName);
        	}
		}
	}
	
	@Test
	public void submitSuccess() throws Exception {
		this.mockMvc.perform(
			post("/bbs")
				.param("subject", "create test")
				.param("content", "내용")
				.param("writer", "tester")
		)
		.andExpect(status().isMovedTemporarily())
		.andExpect(redirectedUrl("/bbs"));
	}
	
	@Test
	public void submitError() throws Exception {
		this.mockMvc.perform(
			post("/bbs")
				.param("subject", "create test")
				.param("content", "내용")
//				.param("writer", "tester")
		)
		.andExpect(status().isOk())
		.andExpect(view().name("bbs/form"))
		.andExpect(model().errorCount(1))
		.andExpect(model().attributeHasFieldErrors("bbs", "writer"));
	}
	
	@Test
	public void findById() throws Exception {
		
		Bbs found = new Bbs("create test", "내용", "tester");

		when(bbsServiceMock.findById(ID)).thenReturn(found);
		
		this.mockMvc.perform(
			get("/bbs/" + ID)
		)
		.andExpect(status().isOk())
		.andExpect(view().name("bbs/view"))
		.andExpect(model().attribute("item", notNullValue()))
		.andExpect(model().attribute("item", hasProperty("id", is(nullValue()))))
		.andExpect(model().attribute("item", hasProperty("subject", is("create test"))))
		.andExpect(model().attribute("item", hasProperty("content", is("내용"))))
		.andExpect(model().attribute("item", hasProperty("writer", is("tester"))))
		.andExpect(model().size(1));
		
		verify(bbsServiceMock, times(1)).findById(ID);
        verifyNoMoreInteractions(bbsServiceMock);
	}
	
	@Test
	public void findAll() throws Exception {
		
		Bbs first = new Bbs("create test", "내용", "tester");
		Bbs second = new Bbs("create test2", "내용2", "tester");

		when(bbsServiceMock.findAll()).thenReturn(Arrays.asList(first, second));
		
		this.mockMvc.perform(
			get("/bbs")
		)
		.andExpect(status().isOk())
		.andExpect(view().name("bbs/list"))
        .andExpect(model().attribute("items", hasSize(2)))
        .andExpect(model().attribute("items", hasItem(
                allOf(
                        hasProperty("id", is(nullValue())),
                        hasProperty("subject", is("create test")),
                        hasProperty("content", is("내용")),
                        hasProperty("writer", is("tester"))
                )
        )))
        .andExpect(model().attribute("items", hasItem(
                allOf(
                        hasProperty("id", is(nullValue())),
                        hasProperty("subject", is("create test2")),
                        hasProperty("content", is("내용2")),
                        hasProperty("writer", is("tester"))
                )
        )));
	}

}
