package com.jnotes.DAO;

import com.jnotes.Application;
import com.jnotes.models.NotesEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;

/**
 * Created by Skylar on 9/15/2014.
 */
public class NoteDAO {
	public NotesEntity createNote(String title, String text) {
		Session session = Application.getSession();
		java.util.Date date= new java.util.Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		NotesEntity notesEntity = new NotesEntity();
		notesEntity.setTitle(title);
		notesEntity.setText(text);
		notesEntity.setDateCreated(timestamp);
		try{
			session.save(notesEntity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
		}
		return notesEntity;
	}

	public NotesEntity getNoteById(int id) {
		Session session = Application.getSession();
		NotesEntity notesEntity = null;
		try {
			notesEntity = (NotesEntity)session.get(NotesEntity.class, id);
			Hibernate.initialize(notesEntity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return notesEntity;
	}
}
