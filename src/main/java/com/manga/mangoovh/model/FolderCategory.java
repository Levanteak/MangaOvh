package com.manga.mangoovh.model;

import com.manga.mangoovh.model.enums.EFolderCategory;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "folder_category")
@Data
public class FolderCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_category_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EFolderCategory name;
}
