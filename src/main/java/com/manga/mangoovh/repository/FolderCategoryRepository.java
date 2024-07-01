package com.manga.mangoovh.repository;

import com.manga.mangoovh.model.FolderCategory;
import com.manga.mangoovh.model.enums.EFolderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolderCategoryRepository extends JpaRepository<FolderCategory, Long> {
    Optional<FolderCategory> findByName(EFolderCategory name);
}
