package io.sommers.jnotes.repository;

import io.sommers.jnotes.models.Note;

/**
 * Created by Skylar on 9/22/2014.
 */
public interface NoteRepository {
    Note save(Note note);

	void delete(int id);

    void delete(Note note);

	Note getById(int id);
}
