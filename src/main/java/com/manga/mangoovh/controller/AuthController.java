package com.manga.mangoovh.controller;

import com.manga.mangoovh.config.dto.LoginRequestDTO;
import com.manga.mangoovh.config.dto.ResponseDTO;
import com.manga.mangoovh.config.infra.security.TokenService;
import com.manga.mangoovh.model.Role;
import com.manga.mangoovh.model.User;
import com.manga.mangoovh.model.enums.ERole;
import com.manga.mangoovh.repository.RoleRepository;
import com.manga.mangoovh.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.manga.mangoovh.config.dto.RegisterRequestDTO;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("http://localhost:3000")
@Tag(name = "AuthController Controller", description = "API для Авторизации")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body) {
        try {
            User user = this.userRepository.findByUsername(body.username()).orElseThrow(() -> new RuntimeException("User not found"));
            if (passwordEncoder.matches(body.password(), user.getPassword())) {
                String token = this.tokenService.generateToken(user);
                return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token));
            }
        } catch (RuntimeException e) {
            log.error("Authorization error: " +  e.getMessage(), e);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong username or password");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO requestDTO) {
        Optional<User> existingUser = userRepository.findByUsername(requestDTO.username());

        if (existingUser.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseDTO("Username already exists", null));
        }

        User newUser = new User();
        newUser.setUsername(requestDTO.username());
        newUser.setPassword(passwordEncoder.encode(requestDTO.password()));
        newUser.setEmail(requestDTO.email());
        newUser.setDescription(requestDTO.description());
        newUser.setRating(5L);
        newUser.setDateCreate(LocalDateTime.now());

        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: ROLE_USER is not found."));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        newUser.setRoles(roles);

        userRepository.save(newUser);

        String token = tokenService.generateToken(newUser);
        return ResponseEntity.ok(new ResponseDTO(newUser.getUsername(), token));
    }
}