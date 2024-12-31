package com.example.mangavault.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        Optional<User> userOptional = this.userRepository.findByUsername(user.getUsername());

        if(userOptional.isPresent()) {
            throw new IllegalStateException("Username has been taken already"); // TODO: Create custom exceptions
        }

        return this.userRepository.save(user);            
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(Long userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);

        if(!userOptional.isPresent()) {
            throw new IllegalStateException(String.format("User not found with id: %d", userId)); // TODO: Create custom exceptions
        }

        return userOptional.get();
    }

    public User getUserByUsername(String username) {
        Optional<User> userOptional = this.userRepository.findByUsername(username);

        if(!userOptional.isPresent()) {
            throw new IllegalStateException(String.format("User not found with username: %s", username)); // TODO: Create custom exceptions
        }

        return userOptional.get();
    }

    public User updatedUser(Long userId, User user) {
        Optional<User> userOptional = this.userRepository.findById(userId);
        if(!userOptional.isPresent()) {
            throw new IllegalStateException(String.format("User not found with id: %d", userId)); // TODO: Create custom exceptions
        }

        User existingUser = userOptional.get();
        this.validateUsername(user.getUsername());
        existingUser.setUsername(user.getUsername());  

        return this.userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);

        if(!userOptional.isPresent()) {
            throw new IllegalStateException(String.format("User not found with id: %d", userId)); // TODO: Create custom exceptions
        }

        this.userRepository.deleteById(userId);
    }

    private void validateUsername(String username) {
        if(username == null || username.length() < 6) {
            throw new IllegalStateException("Username must be at least 6 characters"); // TODO: Create custom exceptions
        }

        else if(username.length() > 255) {
            throw new IllegalStateException("Username must be at most 255 characters"); // TODO: Create custom exceptions
        }
    }
}
