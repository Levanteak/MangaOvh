package com.manga.mangoovh.DTO;

import com.manga.mangoovh.model.enums.EMangaStatus;
import lombok.Data;
import com.manga.mangoovh.model.enums.EContentRating;
import com.manga.mangoovh.model.enums.ECountry;
import com.manga.mangoovh.model.enums.EGenre;

import java.time.LocalDateTime;

@Data
public class MangaDTO {
    private Long id;
    private String title;
    private String description;
    private int views;
    private int likes;
    private double rating;
    private int bookmarks;
    private EGenre EGenre;
    private ECountry ECountry;
    private EContentRating EContentRating;
    private EMangaStatus status;
    private LocalDateTime createdAt;
    private MangaUserDTO user;
}