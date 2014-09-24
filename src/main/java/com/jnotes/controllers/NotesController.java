package com.jnotes.controllers;

import com.jnotes.exceptions.ResourceNotCreatedException;
import com.jnotes.exceptions.ResourceNotFoundException;
import com.jnotes.models.NoteEntity;
import com.jnotes.persistence.repository.NoteHibernateRepository;
import com.jnotes.persistence.repository.NoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Skylar on 9/12/2014.
 */
@Controller
public class NotesController {

	NoteRepository noteRepository = new NoteHibernateRepository();

	@RequestMapping(value = "/notes/{noteId}", method = RequestMethod.GET)
	@ResponseBody
	public NoteEntity getNote(@PathVariable("noteId") int id) throws ResourceNotFoundException {
		NoteEntity noteEntity = noteRepository.findById(id);
		if(noteEntity == null) {
			throw new ResourceNotFoundException("Note with id " + id + " could not be found.");
		}
		System.out.println(id);
		return noteEntity;
	}

    @RequestMapping(value = "/notes", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public NoteEntity createNote(@RequestBody NoteEntity noteEntity) throws ResourceNotCreatedException {
        noteRepository.save(noteEntity);
        if(noteEntity.getId() == 0) {
            throw new ResourceNotCreatedException("Note couldn't be created");
        }
        return noteEntity;
    }

    @RequestMapping(value = "/notes/{noteId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteNote(@PathVariable("noteId") int id) throws ResourceNotFoundException {
        NoteEntity noteEntity = noteRepository.findById(id);
        if(noteEntity != null) {
            noteRepository.delete(noteEntity);
        } else {
            throw new ResourceNotFoundException("Note with id " + id + " could not be found");
        }
    }

    @RequestMapping(value = "/notes/{noteId}", method = RequestMethod.PUT)
    @ResponseBody
    public NoteEntity updateNote(@RequestBody NoteEntity noteEntity, @PathVariable("noteId") int id)
        throws ResourceNotFoundException {
        NoteEntity updatedNoteEntity = noteRepository.findById(id);
        if(updatedNoteEntity != null) {
            updatedNoteEntity.setText(noteEntity.getText());
            updatedNoteEntity.setTitle(noteEntity.getTitle());
            noteRepository.save(updatedNoteEntity);
        } else {
            throw new ResourceNotFoundException("Note with id " + id + " could not be found");
        }
        return updatedNoteEntity;
    }
}
