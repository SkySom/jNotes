package com.jnotes.controllers;

import com.jnotes.exceptions.ResourceNotCreatedException;
import com.jnotes.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Skylar on 9/22/2014.
 */
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public String handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ex.getMessage();
	}

    @ExceptionHandler(ResourceNotCreatedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleResourceNotCreatedException(ResourceNotCreatedException ex) {
        return ex.getMessage();
    }
}
