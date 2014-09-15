package com.jnotes.controllers;

import com.jnotes.models.NotesEntity;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

/**
 * Created by Skylar on 9/12/2014.
 */
@Controller
@RequestMapping("/notes")
public class NotesController {
	@RequestMapping(method= RequestMethod.GET)
	public @ResponseBody NotesEntity getNote() {
		NotesEntity note = new NotesEntity();
		return note;
	}
}
