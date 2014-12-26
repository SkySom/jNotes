package com.jnotes.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Skylar on 9/24/2014.
 */
@Entity
@Table(name = "users", schema = "public", catalog = "notes")
public class User {
    private int id;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private Date dateCreated;
    private Date dateUpdated;
    private UserStatus userStatus;

    public interface WithoutPasswordView {};
    public interface WithPasswordView extends WithoutPasswordView {};

    public User() {
    }

    public User(String username, String password, UserStatus userStatus) {
        this.username = username;
        this.password = password;
        this.userStatus = userStatus;
    }

    public User(String username, String password, String confirmPassword, String email, UserStatus userStatus) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.userStatus = userStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    @JsonView(WithoutPasswordView.class)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", unique = true, nullable = false, length = 45)
    @JsonView(WithoutPasswordView.class)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 60)
    @JsonView(WithPasswordView.class)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Confirm Password needn't be stored in the database. Only useful for json views.
    @JsonView(WithPasswordView.class)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Basic
    @Column(name = "email")
    @JsonView(WithoutPasswordView.class)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false)
    @JsonView(WithoutPasswordView.class)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_updated", nullable = false)
    @JsonView(WithoutPasswordView.class)
    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "userstatus", nullable = false)
    @JsonView(WithoutPasswordView.class)
    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}

