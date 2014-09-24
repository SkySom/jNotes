package com.jnotes.persistence.repository;

import com.jnotes.models.User;

/**
 * Created by Skylar on 9/24/2014.
 */
public interface UserRepository {
    public User getByUsername(String username);
}
