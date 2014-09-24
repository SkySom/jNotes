package com.jnotes.persistence.repository;

import com.jnotes.Application;
import com.jnotes.models.Note;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * Created by Skylar on 9/22/2014.
 */
public class NoteHibernateRepository implements NoteRepository {
	@Override
	public Note save(Note note) {
        Session session = null;
        Transaction transaction = null;

        Date currentTime = new Date();
        if(note.getDateCreated() == null) {
            note.setDateCreated(currentTime);
        }
        note.setDateUpdated(currentTime);

        try {
            session = Application.getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(note);
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
        return note;
	}

	@Override
	public void delete(int id) {
        Note note = null;
        note = findById(id);
        if(note != null) {
            delete(note);
        }
	}

    @Override
    public void delete(Note note) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Application.getSession();
            transaction = session.beginTransaction();
            if(note != null) {
                session.delete(note);
            }
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
    }

    @Override
	public Note findById(int id) {
		Session session = null;
		Note note = null;
		try {
			session = Application.getSession();
			note = (Note)session.get(Note.class, id);
			Hibernate.initialize(note);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return note;
	}
}
