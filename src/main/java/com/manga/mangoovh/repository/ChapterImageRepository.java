package com.manga.mangoovh.repository;

import com.manga.mangoovh.model.ChapterImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterImageRepository extends JpaRepository<ChapterImage, Long> {
}
