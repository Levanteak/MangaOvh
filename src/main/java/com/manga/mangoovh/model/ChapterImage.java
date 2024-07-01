package com.manga.mangoovh.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chapter_images")
@Data
public class ChapterImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_images_id")
    private Long id;

    @Lob
    private byte[] image;

    private int orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

}