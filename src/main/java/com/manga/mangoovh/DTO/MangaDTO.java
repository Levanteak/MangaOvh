package com.manga.mangoovh.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("EGenre")
    private EGenre egenre;

    @JsonProperty("ECountry")
    private ECountry ecountry;

    @JsonProperty("EContentRating")
    private EContentRating econtentRating;

    @JsonProperty("status")
    private EMangaStatus status;
    private LocalDateTime createdAt;
    private UserToMangaDTO user;
}