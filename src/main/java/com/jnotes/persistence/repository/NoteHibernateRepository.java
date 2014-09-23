package com.jnotes.persistence.repository;

import com.jnotes.Application;
import com.jnotes.models.NoteCreation;
import com.jnotes.models.NoteEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 * Created by Skylar on 9/22/2014.
 */
public class NoteHibernateRepository implements NoteRepository {
	@Override
	public NoteEntity save(NoteEntity noteEntity) {
        Session session = null;
        try {
            session = Application.getSession();
            session.save(noteEntity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return noteEntity;
	}

	@Override
	public void delete(int id) {

	}

	@Override
	public NoteEntity findById(int id) {
		Session session = null;
		NoteEntity noteEntity = null;
		try {
			session = Application.getSession();
			noteEntity = (NoteEntity)session.get(NoteEntity.class, id);
			Hibernate.initialize(noteEntity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return noteEntity;
	}
}
