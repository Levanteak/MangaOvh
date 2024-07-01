package com.manga.mangoovh.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
@Data
public class UserToMangaDTO {
    private Long userId;
    private String username;
    private String email;
    private String description;
    private LocalDateTime dateCreate;
    private Long rating;
    private Set<MangaDTO> mangas;
}
