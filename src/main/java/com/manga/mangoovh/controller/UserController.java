package com.manga.mangoovh.controller;


import com.manga.mangoovh.DTO.MangaDTO;
import com.manga.mangoovh.DTO.UserAvatarDTO;
import com.manga.mangoovh.DTO.UserDTO;
import com.manga.mangoovh.model.Manga;
import com.manga.mangoovh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsersDTO();
        log.info("users: " +  users);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        Optional<UserDTO> user = userService.getUserDTOByUsername(username);
        log.info("username: " +  user);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/avatar/{userId}")
    public ResponseEntity<UserAvatarDTO> getUserByUserId(@PathVariable Long userId) {
        Optional<UserAvatarDTO> user = userService.getUserAvatarDTOByUserId(userId);
        log.info("username: " +  user);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        Optional<UserDTO> existingUser = userService.getUserDTOByUsername(userDTO.getUsername());
        log.info("existingUser: " +  existingUser);
        if (existingUser.isPresent() && existingUser.get().getUserId().equals(userId)) {
            userService.updateExistingUser(existingUser.get(), userDTO);
            return ResponseEntity.ok(existingUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/avatar/{userId}")
    public ResponseEntity<String> uploadAvatar(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        try {
            userService.addAvatarToUser(userId, file.getBytes());
            return ResponseEntity.ok("Avatar uploaded successfully for user with ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload avatar: " + e.getMessage());
        }
    }

    @PostMapping("/{userId}/mangas")
    public ResponseEntity<MangaDTO> addMangaToUser(@PathVariable Long userId, @RequestBody MangaDTO mangaDTO) {
        MangaDTO manga = userService.addMangaToUser(userId, mangaDTO);
        return ResponseEntity.ok(manga);
    }
}