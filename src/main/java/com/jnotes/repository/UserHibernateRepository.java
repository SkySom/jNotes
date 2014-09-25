package com.jnotes.repository;

import com.jnotes.Application;
import com.jnotes.models.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;

/**
 * Created by Skylar on 9/24/2014.
 */
public class UserHibernateRepository implements UserRepository {
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
}
