package com.example.mangavault.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.example.mangavault.exception.ApiRequestException;

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

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        when(userRepository.save(any(User.class))).thenReturn(user);

        User userCreated = this.userService.createUser(user);

        Assertions.assertThat(userCreated).isNotNull();
        Assertions.assertThat(userCreated.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void UserService_CreateUser_ThrowsApiRequestException() {
        User user = User.builder()
            .username("SomeUserName")
            .build();

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        ApiRequestException exception = assertThrows(ApiRequestException.class, () -> {
            userService.createUser(user);
        });

        Assertions.assertThat(exception.getMessage()).isEqualTo("Username has been taken already");
        Assertions.assertThat(exception.geHttpStatus()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    public void UserService_getUsers_ReturnsUsers() {
        User user = User.builder()
            .username("SomeTest")
            .build();

        User user2 = User.builder()
            .username("SEcond")
            .build();
    
        User user3 = User.builder()
        .username("THIRD")
        .build();

        List<User> users = List.of(user, user2, user3);

        when(userRepository.findAll()).thenReturn(users);
        
        List<User> foundUsers = this.userService.getUsers();
        
        Assertions.assertThat(foundUsers.size()).isEqualTo(3);

        Assertions.assertThat(foundUsers.get(0).getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(foundUsers.get(1).getUsername()).isEqualTo(user2.getUsername());
        Assertions.assertThat(foundUsers.get(2).getUsername()).isEqualTo(user3.getUsername());
    }

    @Test
    public void UserService_getUserById_ReturnsUsers() {
        User user = User.builder()
            .username("null")
            .build();

        Optional<User> userOptional = Optional.of(user);

        Long userId = 4L;
        when(userRepository.findById(userId)).thenReturn(userOptional);

        User userFound = this.userService.getUserById(userId);

        Assertions.assertThat(userFound.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void UserService_GetUserById_ThrowsApiRequestException() {
        Long userId = 5L;

        when(userRepository.findById((userId))).thenReturn(Optional.empty());

        ApiRequestException exception = assertThrows(ApiRequestException.class, () -> {
            userService.getUserById(userId);
        });

        Assertions.assertThat(exception.getMessage()).isEqualTo(String.format("User not found with id: %d", userId));
        Assertions.assertThat(exception.geHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    
    @Test
    public void UserService_getUserByUsername_ReturnsUser() {
        User user = User.builder()
            .username("TheuserName")
            .build();

        Optional<User> userOptional = Optional.of(user);

        when(userRepository.findByUsername(user.getUsername())).thenReturn(userOptional);
        
        User userFound = this.userService.getUserByUsername(user.getUsername());

        Assertions.assertThat(userFound.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void UserService_GetUserByUsername_ThrowsApiRequestException() {
        String username = "SomeUserNmae";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        ApiRequestException exception = assertThrows(ApiRequestException.class, () -> {
            userService.getUserByUsername(username);
        });

        Assertions.assertThat(exception.getMessage()).isEqualTo(String.format("User not found with username: %s", username));
        Assertions.assertThat(exception.geHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void UserService_updateUser_() {

    }

    @Test
    public void UserService_deleteUser() {

    }
}
