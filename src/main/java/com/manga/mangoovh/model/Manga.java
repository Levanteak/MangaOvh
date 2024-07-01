package com.manga.mangoovh.model;

import com.manga.mangoovh.model.enums.EContentRating;
import com.manga.mangoovh.model.enums.ECountry;
import com.manga.mangoovh.model.enums.EGenre;
import com.manga.mangoovh.model.enums.EMangaStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mangas")
@Data
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manga_id")
    private Long id;
    private String title;
    private String description;
    private int views;
    private int likes;
    private double rating;
    private int bookmarks;

    @Enumerated(EnumType.STRING)
    private EGenre EGenre;

    @Enumerated(EnumType.STRING)
    private ECountry ECountry;

    @Enumerated(EnumType.STRING)
    private EContentRating EContentRating;

    @Enumerated(EnumType.STRING)
    private EMangaStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Chapter> chapters = new HashSet<>();
    @OneToMany(mappedBy = "manga", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MangaTitleImage> titleImages = new HashSet<>();
}