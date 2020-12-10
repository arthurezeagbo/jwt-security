package com.example.securitydemo.domain;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public long getUserId() {
        return id;
    }

    public void setUserId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getUsername(), getPassword(), getEmail());
    }
}
