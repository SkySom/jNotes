package io.sommers.jnotes.repository;

import io.sommers.jnotes.Application;
import io.sommers.jnotes.models.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Skylar on 9/24/2014.
 */
public class UserHibernateRepository implements UserRepository {
    @Override
    public User save(User user) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = Application.getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
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
        return user;
    }

    @Override
    public void delete(int id) {
        User user = getById(id);
        if(user != null) {
            delete(user);
        }
    }

    @Override
    public void delete(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Application.getSession();
            transaction = session.beginTransaction();
            if(user != null) {
                session.delete(user);
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
    public User getByUsername(String username) {
        Session session = null;
        User user = null;
        try {
            session = Application.getSession();
            user = (User)session.get(User.class, username);
            Hibernate.initialize(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    @Override
    public User getById(int id) {
        Session session = null;
        User user = null;
        try {
            session = Application.getSession();
            user = (User)session.get(User.class, id);
            Hibernate.initialize(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }
}
