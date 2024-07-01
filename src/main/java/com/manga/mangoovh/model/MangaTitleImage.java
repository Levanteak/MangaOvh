package com.manga.mangoovh.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "manga_title_images")
@Data
public class MangaTitleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manga_title_images_id")
    private Long id;

    @Lob
    private byte[] imageData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manga_id", nullable = false)
    private Manga manga;
}