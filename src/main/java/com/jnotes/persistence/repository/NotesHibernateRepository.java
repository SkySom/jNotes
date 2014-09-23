package com.jnotes.persistence.repository;

import com.jnotes.Application;
import com.jnotes.models.NotesEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 * Created by Skylar on 9/22/2014.
 */
public class NotesHibernateRepository implements NotesRepository {

	@Override
	public NotesEntity save(NotesEntity notesEntity) {
		return null;
	}

	@Override
	public void delete(int id) {

	}

	@Override
	public NotesEntity findById(int id) {
		Session session = null;
		NotesEntity notesEntity = null;
		try {
			session = Application.getSession();
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
