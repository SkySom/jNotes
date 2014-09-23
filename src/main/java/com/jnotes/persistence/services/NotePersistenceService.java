package com.jnotes.persistence.services;

import com.jnotes.models.NoteEntity;

/**
 * Created by Skylar on 9/22/2014.
 */
public interface NotePersistenceService {

	public NoteEntity getNote(int id);

	public NoteEntity createNote(NoteEntity noteEntity);

	public boolean deleteNote(int id);
}
