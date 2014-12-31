package com.jnotes.tests;

import com.jnotes.controllers.UserController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by Skylar on 12/25/2014.
 */
public class UserControllerTests {
	MockMvc mockMvc;
	UserController userController;

	@Before
	public void setup() {
		userController = new UserController();
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	}

	@Test
	public void CreateNewUser() {

	}

	@Test
	public void GetNotExistentUser() throws Exception {
		//mockMvc.perform(get("/users/{userId}", 0))
				//.andExpect(status().isNotFound());
	}
}
