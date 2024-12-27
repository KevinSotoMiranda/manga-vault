package com.example.mangavault.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String username;

    public User(String username, Long id) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                '}';
    }
}
