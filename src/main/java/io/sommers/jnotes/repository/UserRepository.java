package io.sommers.jnotes.repository;

import io.sommers.jnotes.models.User;

/**
 * Created by Skylar on 9/24/2014.
 */
public interface UserRepository {
    User save(User user);

    void delete(int id);

    void delete(User user);

    User getByUsername(String username);

    User getById(int id);
}
