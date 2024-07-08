package com.manga.mangoovh.controller;


import com.manga.mangoovh.DTO.UserDTO;
import com.manga.mangoovh.DTO.UserDetailsDTO;
import com.manga.mangoovh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/profile/{userId}")
    public ResponseEntity<UserDTO> updateUserProfile(@PathVariable Long userId, @ModelAttribute UserDTO updatedUserDTO) {
        UserDTO updatedUser = userService.updateUserProfile(userId, updatedUserDTO);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDetailsDTO> getUserById(@PathVariable Long userId) {
        UserDetailsDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDetailsDTO> getUserByUsername(@PathVariable String username) {
        UserDetailsDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDetailsDTO>> getAllUsers() {
        List<UserDetailsDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}