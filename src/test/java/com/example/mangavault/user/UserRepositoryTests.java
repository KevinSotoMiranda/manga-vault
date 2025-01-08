package com.example.mangavault.user;

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
    public void UserRepository_Save_ReturnSavedUsers() {
        // Arrange
        User user = User.builder()
            .username("someUser").build();

        // Act
        User userSaved = this.userRepository.save(user);

        // Assert
        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isGreaterThan(0);
    }
    
}
