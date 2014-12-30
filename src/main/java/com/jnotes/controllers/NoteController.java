package com.jnotes.controllers;

import com.jnotes.exceptions.ResourceNotCreatedException;
import com.jnotes.exceptions.ResourceNotFoundException;
import com.jnotes.models.Note;
import com.jnotes.repository.NoteHibernateRepository;
import com.jnotes.repository.NoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Skylar on 9/12/2014.
 */
@Controller
public class NoteController {

	NoteRepository noteRepository = new NoteHibernateRepository();

	@RequestMapping(value = "/notes/{noteId}", method = RequestMethod.GET)
	@ResponseBody
	public Note getNote(@PathVariable("noteId") int id) throws ResourceNotFoundException {
		Note note = noteRepository.getById(id);
		if(note == null) {
			throw new ResourceNotFoundException("Note with id " + id + " could not be found.");
		}
		return note;
	}

    @RequestMapping(value = "/notes", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Note createNote(@RequestBody Note note) throws ResourceNotCreatedException {
        note = noteRepository.save(note);
        if(note.getId() == 0) {
            throw new ResourceNotCreatedException("Note couldn't be created");
        }
        return note;
    }

    @RequestMapping(value = "/notes/{noteId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteNote(@PathVariable("noteId") int id) throws ResourceNotFoundException {
        Note note = noteRepository.getById(id);
        if(note != null) {
            noteRepository.delete(note);
        } else {
            throw new ResourceNotFoundException("Note with id " + id + " could not be found");
        }
    }

    @RequestMapping(value = "/notes/{noteId}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseBody
    public Note updateNote(@RequestBody Note note, @PathVariable("noteId") int id)
        throws ResourceNotFoundException {
        Note updatedNote = noteRepository.getById(id);
        if(updatedNote != null) {
            updatedNote.setText(note.getText());
            updatedNote.setTitle(note.getTitle());
            noteRepository.save(updatedNote);
        } else {
            throw new ResourceNotFoundException("Note with id " + id + " could not be found");
        }
        updatedNote = noteRepository.save(updatedNote);
        return updatedNote;
    }
}
