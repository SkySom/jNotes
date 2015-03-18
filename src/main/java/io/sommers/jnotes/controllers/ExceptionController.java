package io.sommers.jnotes.controllers;

import io.sommers.jnotes.exceptions.ResourceNotCreatedException;
import io.sommers.jnotes.exceptions.ResourceNotFoundException;
import io.sommers.jnotes.exceptions.ResourceNotUpdatedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Skylar on 9/22/2014.
 */
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        return new ResponseEntity<String>("{\"error\":\"" + ex.getMessage() + "\"}", httpHeaders, HttpStatus.NOT_FOUND);
	}

    @ExceptionHandler(ResourceNotCreatedException.class)
    @ResponseBody
    public ResponseEntity<String> handleResourceNotCreatedException(ResourceNotCreatedException ex) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        return new ResponseEntity<String>("{\"error\":\"" + ex.getMessage() + "\"}", httpHeaders, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotUpdatedException.class)
    @ResponseBody
    public ResponseEntity<String> handleResourceNotUpdatedException(ResourceNotUpdatedException ex) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        return new ResponseEntity<String>("{\"error\":\"" + ex.getMessage() + "\"}", httpHeaders, HttpStatus.BAD_REQUEST);
    }
}
