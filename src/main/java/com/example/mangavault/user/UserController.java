package com.example.mangavault.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(path = "api/v1/users")
@Validated
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users =  this.userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
        User user = this.userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = this.userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = this.userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody User user) {
        this.userService.updatedUser(userId, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        this.userService.deleteUser(userId);
    }
}
