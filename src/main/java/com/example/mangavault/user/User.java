package com.example.mangavault.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "user_id")
    private Long id;

    @Size(min = 6, message = "Username must be at least 6 characters long", max = 255)
    @Column(name = "username")
    @Setter
    private String username;

    public User(String username) {
        this.username = username;
    }

    private User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id + 
                ", username='" + username + '\'' +
                '}';
    }
}
