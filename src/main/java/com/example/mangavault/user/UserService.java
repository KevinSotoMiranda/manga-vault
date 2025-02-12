package com.example.mangavault.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.mangavault.exception.ApiRequestException;

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
            throw new ApiRequestException("Username has been taken already", HttpStatus.CONFLICT);
        }

        return this.userRepository.save(user);            
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(Long userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);

        if(!userOptional.isPresent()) {
            throw new ApiRequestException(String.format("User not found with id: %d", userId), HttpStatus.NOT_FOUND);
        }

        return userOptional.get();
    }

    public User getUserByUsername(String username) {
        Optional<User> userOptional = this.userRepository.findByUsername(username);

        if(!userOptional.isPresent()) {
            throw new ApiRequestException(String.format("User not found with username: %s", username), HttpStatus.NOT_FOUND);
        }

        return userOptional.get();
    }

    public User updateUser(Long userId, User user) {
        Optional<User> userOptional = this.userRepository.findById(userId);

        if(!userOptional.isPresent()) {
            throw new ApiRequestException(String.format("User not found with id: %d", userId), HttpStatus.NOT_FOUND); 
        }

        User existingUser = userOptional.get();
        this.validateUsername(user.getUsername());
        existingUser.setUsername(user.getUsername());  

        return this.userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        Optional<User> userOptional = this.userRepository.findById(userId);

        if(!userOptional.isPresent()) {
            throw new ApiRequestException(String.format("User not found with id: %d", userId), HttpStatus.NOT_FOUND);
        }

        this.userRepository.deleteById(userId);
    }

    private void validateUsername(String username) {
        if(username == null || username.length() < 6) {
            throw new ApiRequestException("Username must be at least 6 characters", HttpStatus.BAD_REQUEST); 
        }

        else if(username.length() > 255) {
            throw new ApiRequestException("Username must be at most 255 characters", HttpStatus.BAD_REQUEST);
        }
    }
}
