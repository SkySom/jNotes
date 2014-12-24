package com.jnotes.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Skylar on 9/24/2014.
 */
@Entity
@Table(name = "users", schema = "public", catalog = "notes")
public class User {
    private String username;
    private String password;
    private String email;
    private UserStatus userStatus;

    public User() {
    }

    public User(String username, String password, UserStatus userStatus) {
        this.username = username;
        this.password = password;
        this.userStatus = userStatus;
    }

    @Id
    @Column(name = "username", unique = true, nullable = false, length = 45)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 60)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "userstatus", nullable = false)
    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}

