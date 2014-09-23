package com.jnotes.persistence.services;

import com.jnotes.models.NotesEntity;

/**
 * Created by Skylar on 9/22/2014.
 */
public interface NotePersistenceService {

	public NotesEntity getNote(int id);

	public NotesEntity createNote(NotesEntity notesEntity);

	public boolean deleteNote(int id);
}
