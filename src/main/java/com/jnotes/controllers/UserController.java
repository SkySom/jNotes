package com.jnotes.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.jnotes.exceptions.ResourceNotCreatedException;
import com.jnotes.models.User;
import com.jnotes.repository.UserHibernateRepository;
import com.jnotes.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Skylar on 12/24/2014.
 */
public class UserController {
	UserRepository userRepository = new UserHibernateRepository();

	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	@JsonView(User.WithoutPasswordView.class)
	public User createUser(@RequestBody User user) throws ResourceNotCreatedException {
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			throw new ResourceNotCreatedException("Passwords do not match.");
		}
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		user = userRepository.save(user);
		return user;
	}
}
