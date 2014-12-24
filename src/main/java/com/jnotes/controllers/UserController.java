package com.jnotes.controllers;

import com.jnotes.exceptions.ResourceNotCreatedException;
import com.jnotes.models.User;
import com.jnotes.repository.UserHibernateRepository;
import com.jnotes.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Skylar on 12/24/2014.
 */
public class UserController {
	UserRepository userRepository = new UserHibernateRepository();

	@RequestMapping(value = "/notes", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public User createUser(@RequestBody User user) throws ResourceNotCreatedException {
		return user;
	}
}
