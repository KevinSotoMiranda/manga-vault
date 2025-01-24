package com.example.mangavault.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void UserService_CreateUser_ReturnsUser() {
        User user = User.builder()
            .username("testuser")
            .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        User userCreated = this.userService.createUser(user);

        Assertions.assertThat(userCreated).isNotNull();
        Assertions.assertThat(userCreated.getUsername()).isEqualTo(user.getUsername());

    }
    
}
