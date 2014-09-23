package com.jnotes.persistence.repository;

import com.jnotes.Application;
import com.jnotes.models.NoteEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * Created by Skylar on 9/22/2014.
 */
public class NoteHibernateRepository implements NoteRepository {
	@Override
	public NoteEntity save(NoteEntity noteEntity) {
        Session session = null;
        Transaction transaction = null;

        Date currentTime = new Date();
        if(noteEntity.getDateCreated() == null) {
            noteEntity.setDateCreated(currentTime);
        }
        noteEntity.setDateUpdated(currentTime);

        try {
            session = Application.getSession();
            transaction = session.beginTransaction();
            session.save(noteEntity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
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
