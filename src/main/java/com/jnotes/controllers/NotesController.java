package com.jnotes.controllers;

import com.jnotes.exceptions.ResourceNotFoundException;
import com.jnotes.models.NotesEntity;
import com.jnotes.persistence.repository.NotesHibernateRepository;
import com.jnotes.persistence.repository.NotesRepository;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * Created by Skylar on 9/12/2014.
 */
@Controller
public class NotesController {

	NotesRepository notesRepository = new NotesHibernateRepository();

	@RequestMapping(value = "/notes/{noteId}", method = RequestMethod.GET)
	@ResponseBody
	public NotesEntity getNote(@PathVariable("noteId") int id) throws ResourceNotFoundException {
		NotesEntity notesEntity = notesRepository.findById(id);
		if(notesEntity == null) {
			throw new ResourceNotFoundException("Note with id " + id + " could not be found.");
		}
		System.out.println(id);
		return notesEntity;
	}
}
