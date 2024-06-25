package com.manga.mangoovh.config.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.manga.mangoovh.config.infra.security.CustomRoleDeserializer;
import com.manga.mangoovh.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public record RegisterRequestDTO (
        @NotBlank(message = "Username is required")
        String username,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password should be at least 6 characters")
        String password,

        @NotBlank(message = "Description is required")
        String description,

        @JsonDeserialize(using = CustomRoleDeserializer.class)
        Set<Role> role,

        MultipartFile avatar
) {}