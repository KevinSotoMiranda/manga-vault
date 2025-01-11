package com.example.mangavault.user;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {
    @Autowired 
    private UserRepository userRepository;

    @Test
    public void UserRepository_Save_ReturnSavedUser() {
        User user = User.builder()
            .username("someUser").build();

        User userSaved = this.userRepository.save(user);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isGreaterThan(0);
        Assertions.assertThat(userSaved.getUsername()).isEqualTo("someUser");
    }
    
    @Test
    public void UserRepository_SaveAll_ReturnSavedUsers() {
        List<User> users = List.of(
            User.builder()
                .username("happyCats")
                .build(),
            User.builder()
                .username("oneOther")
                .build(),
            User.builder()
                .username("SomethingRandom")
                .build()
        );
            
        List<User> usersSaved = this.userRepository.saveAll(users);

        Assertions.assertThat(users).isNotNull();
        Assertions.assertThat(usersSaved.size()).isEqualTo(3);

        Assertions.assertThat(usersSaved.get(0).getId()).isGreaterThan(0);
        Assertions.assertThat(usersSaved.get(0).getUsername()).isEqualTo("happyCats");

        Assertions.assertThat(usersSaved.get(1).getId()).isGreaterThan(0);
        Assertions.assertThat(usersSaved.get(1).getUsername()).isEqualTo("oneOther");

        Assertions.assertThat(usersSaved.get(2).getId()).isGreaterThan(0);
        Assertions.assertThat(usersSaved.get(2).getUsername()).isEqualTo("SomethingRandom");       
    }

    @Test
    public void UserRepository_DeleteById_ReturnsEmptyOptional() {
        User user = User.builder()
            .username("deleteMe")
            .build();

        User userSaved = this.userRepository.save(user);

        this.userRepository.deleteById(userSaved.getId());

        Assertions.assertThat(this.userRepository.findById(userSaved.getId())).isEmpty();
    }

    @Test
    public void UserRepository_DeleteAllById_ReturnsEmptyOptional() {
        List<User> users = List.of(
            User.builder()
            .username("deleteMe")
            .build(),
            User.builder()
            .username("deleteMe2")
            .build(),
            User.builder()
            .username("deleteMe3")
            .build()
        );

        List<User> usersSaved = this.userRepository.saveAll(users);

        List<Long> ids = List.of(
            usersSaved.get(0).getId(),
            usersSaved.get(1).getId(),
            usersSaved.get(2).getId()
        );

        this.userRepository.deleteAllById(ids);

        Assertions.assertThat(this.userRepository.findById(ids.get(0))).isEmpty();
        Assertions.assertThat(this.userRepository.findById(ids.get(1))).isEmpty();
        Assertions.assertThat(this.userRepository.findById(ids.get(2))).isEmpty();
    }

    @Test
    public void UserRepository_FindByUsername_ReturnsEmptyOptional() {
        User user = User.builder()
            .username("findMe")
            .build();
        
        User userSaved = this.userRepository.save(user);
        Optional<User> userOptional = this.userRepository.findByUsername(user.getUsername()); 

        Assertions.assertThat(userOptional).isNotEmpty();
        Assertions.assertThat(userSaved.getId()).isEqualTo(userOptional.get().getId());
    }
}