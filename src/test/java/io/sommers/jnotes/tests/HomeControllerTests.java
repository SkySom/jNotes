package io.sommers.jnotes.tests;

import io.sommers.jnotes.controllers.HomeController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Skylar on 12/25/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
@TransactionConfiguration
public class HomeControllerTests {
	MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
	}

	@Test
	public void GetHome() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk());
	}
}
