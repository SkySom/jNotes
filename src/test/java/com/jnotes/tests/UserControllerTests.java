package com.jnotes.tests;

import com.jnotes.Application;
import com.jnotes.controllers.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Skylar on 12/25/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@TransactionConfiguration
public class UserControllerTests extends AbstractJUnit4SpringContextTests {
	MockMvc mockMvc;
	UserController userController;


	@Before
	public void setup() {
		userController = new UserController();
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Rollback(true)
	@Test
	public void CreateNewUser() {

	}

	@Test
	public void GetNotExistentUser() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/users/{userId}", 0))
				.andExpect(status().isNotFound()).andReturn();
	}
}
