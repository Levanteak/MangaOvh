package com.manga.mangoovh.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String description;
    private MultipartFile avatar;
    private String avatarBase64;
}
