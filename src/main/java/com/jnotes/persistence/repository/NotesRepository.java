package com.jnotes.persistence.repository;

import com.jnotes.models.NotesEntity;

/**
 * Created by Skylar on 9/22/2014.
 */
public interface NotesRepository {
	NotesEntity save(NotesEntity notesEntity);

	void delete(int id);

	NotesEntity findById(int id);
}
