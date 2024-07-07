package com.manga.mangoovh.controller;


import com.manga.mangoovh.DTO.UserDTO;
import com.manga.mangoovh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserDTO> updateUserProfile(
            @PathVariable Long userId,
            @ModelAttribute UserDTO updatedUserDTO
    ) {
        UserDTO updatedUser = userService.updateUserProfile(userId, updatedUserDTO);
        return ResponseEntity.ok(updatedUser);
    }
}