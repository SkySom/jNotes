package com.jnotes.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.jnotes.exceptions.ResourceNotCreatedException;
import com.jnotes.exceptions.ResourceNotFoundException;
import com.jnotes.models.User;
import com.jnotes.repository.UserHibernateRepository;
import com.jnotes.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;


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

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteUser(@PathVariable("noteId") int id) throws ResourceNotFoundException {
		User note = userRepository.getById(id);
		if(note != null) {
			userRepository.delete(note);
		} else {
			throw new ResourceNotFoundException("Note with id " + id + " could not be found");
		}
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseBody
	@JsonView(User.WithoutPasswordView.class)
	public User updateUser(@RequestBody User user, @PathVariable("userId") int id) throws ResourceNotFoundException {
		User updatedUser = userRepository.getById(id);
		if(updatedUser != null) {

		} else {
			throw new ResourceNotFoundException("User with id " + id + " could not be found");
		}
		return updatedUser;
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	@ResponseBody
	@JsonView(User.WithoutPasswordView.class)
	public User getUser(@PathVariable("userId") int id) throws ResourceNotFoundException {
		User user = userRepository.getById(id);
		if(user == null) {
			throw new ResourceNotFoundException("User with id " + id + " could not be found.");
		}
		return user;
	}
}
