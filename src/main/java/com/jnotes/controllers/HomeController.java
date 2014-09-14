package com.jnotes.controllers;

import com.jnotes.models.NotesEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Skylar on 9/14/2014.
 */
@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping(method = RequestMethod.GET)
	public String greeting(Model model) {
		return "main";
	}
}
