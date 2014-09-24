package com.jnotes.persistence.services;

import com.jnotes.models.Note;

/**
 * Created by Skylar on 9/22/2014.
 */
public interface NotePersistenceService {

	public Note getNote(int id);

	public Note createNote(Note note);

	public boolean deleteNote(int id);
}
