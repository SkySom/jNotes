package com.jnotes.persistence.repository;

import com.jnotes.models.NoteEntity;

/**
 * Created by Skylar on 9/22/2014.
 */
public interface NoteRepository {
    NoteEntity save(NoteEntity noteEntity);

	void delete(int id);

	NoteEntity findById(int id);
}
