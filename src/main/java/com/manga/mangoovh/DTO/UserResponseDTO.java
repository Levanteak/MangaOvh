package com.manga.mangoovh.DTO;

import com.manga.mangoovh.model.enums.EContentRating;
import com.manga.mangoovh.model.enums.ECountry;
import com.manga.mangoovh.model.enums.EGenre;
import com.manga.mangoovh.model.enums.EMangaStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private String description;
    private LocalDateTime dateCreate;
    private Long rating;
    private Set<Manga> mangas;

    @Data
    public static class Manga {
        private Long id;
        private String title;
        private String description;
        private int views;
        private int likes;
        private double rating;
        private int bookmarks;
        private EGenre egenre;
        private ECountry ecountry;
        private EContentRating econtentRating;
        private EMangaStatus status;
        private LocalDateTime createdAt;
    }
}
