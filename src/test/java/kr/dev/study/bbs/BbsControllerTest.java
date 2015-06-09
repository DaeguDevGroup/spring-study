package kr.dev.study.bbs;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.hamcrest.Matchers.hasProperty;
import kr.dev.study.bbs.domain.Bbs;
import kr.dev.study.bbs.service.BbsService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
public class BbsControllerTest {
	
    private static final Integer ID = 1;
	
	@Mock
	private BbsService bbsServiceMock;
	
	@InjectMocks
	private BbsController bbsController;
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(bbsController)
//				.alwaysDo(print())
				.build();
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
		.andExpect(model().size(1));
		
		verify(bbsServiceMock, times(1)).findById(ID);
        verifyNoMoreInteractions(bbsServiceMock);
	}

}
