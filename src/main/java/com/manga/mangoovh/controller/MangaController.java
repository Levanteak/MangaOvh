package com.manga.mangoovh.controller;

import com.manga.mangoovh.DTO.MangaDTO;
import com.manga.mangoovh.model.Manga;
import com.manga.mangoovh.model.User;
import com.manga.mangoovh.repository.MangaRepository;
import com.manga.mangoovh.repository.UserRepository;
import com.manga.mangoovh.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @PostMapping("/users/{userId}/mangas")
    public ResponseEntity<MangaDTO> addMangaToUser(@PathVariable Long userId, @RequestBody Manga mangaRequest) {
        MangaDTO mangaDTO = mangaService.addMangaToUser(userId, mangaRequest);
        return ResponseEntity.ok(mangaDTO);
    }
    @PostMapping("/mangas/{mangaId}")
    public ResponseEntity<MangaDTO> updateManga(@PathVariable Long mangaId, @RequestBody Manga mangaRequest) {
        MangaDTO mangaDTO = mangaService.updateManga(mangaId, mangaRequest);
        return ResponseEntity.ok(mangaDTO);
    }

    @GetMapping("/mangas/{mangaId}")
    public ResponseEntity<MangaDTO> getMangaById(@PathVariable Long mangaId) {
        MangaDTO mangaDTO = mangaService.getMangaById(mangaId);
        return ResponseEntity.ok(mangaDTO);
    }

    @GetMapping("/mangas")
    public ResponseEntity<List<MangaDTO>> getAllMangas() {
        List<MangaDTO> mangaDTOs = mangaService.getAllMangas();
        return ResponseEntity.ok(mangaDTOs);
    }

}
