package kr.dev.study;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HomeControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void home() throws Exception {
		standaloneSetup(new HomeController()).build()
		.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("home"));
	}

}
