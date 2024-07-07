package com.manga.mangoovh.service;


import com.manga.mangoovh.DTO.MangaDTO;
import com.manga.mangoovh.DTO.MangaUserDTO;
import com.manga.mangoovh.model.Manga;
import com.manga.mangoovh.model.User;
import com.manga.mangoovh.repository.MangaRepository;
import com.manga.mangoovh.repository.UserRepository;
import com.manga.mangoovh.service.Impl.ImplMangaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MangaService implements ImplMangaService {
    private final MangaRepository mangaRepository;
    private final UserRepository userRepository;

    public MangaService(MangaRepository mangaRepository, UserRepository userRepository) {
        this.mangaRepository = mangaRepository;
        this.userRepository = userRepository;
    }

    public MangaDTO addMangaToUser(Long userId, Manga mangaRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        mangaRequest.setUser(user);
        Manga savedManga = mangaRepository.save(mangaRequest);
        log.info("New manga userId : " + userId + ", this manga : " + mangaRequest);
        return toDto(savedManga);
    }
    public MangaDTO updateManga(Long mangaId, Manga mangaRequest) {
        Manga existingManga = mangaRepository.findById(mangaId)
                .orElseThrow(() -> new RuntimeException("Manga not found with id " + mangaId));

        existingManga.setTitle(mangaRequest.getTitle());
        existingManga.setDescription(mangaRequest.getDescription());
        existingManga.setViews(mangaRequest.getViews());
        existingManga.setLikes(mangaRequest.getLikes());
        existingManga.setRating(mangaRequest.getRating());
        existingManga.setBookmarks(mangaRequest.getBookmarks());
        existingManga.setEGenre(mangaRequest.getEGenre());
        existingManga.setECountry(mangaRequest.getECountry());
        existingManga.setEContentRating(mangaRequest.getEContentRating());
        existingManga.setStatus(mangaRequest.getStatus());

        Manga updatedManga = mangaRepository.save(existingManga);
        return toDto(updatedManga);
    }
    public MangaDTO getMangaById(Long mangaId) {
        Manga manga = mangaRepository.findById(mangaId)
                .orElseThrow(() -> new RuntimeException("Manga not found with id " + mangaId));

        return toDto(manga);
    }

    public List<MangaDTO> getAllMangas() {
        List<Manga> mangas = mangaRepository.findAll();
        return mangas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }



    private MangaDTO toDto(Manga manga) {
        if (manga == null) {
            return null;
        }
        MangaDTO mangaDTO = new MangaDTO();
        mangaDTO.setId(manga.getId());
        mangaDTO.setTitle(manga.getTitle());
        mangaDTO.setDescription(manga.getDescription());
        mangaDTO.setViews(manga.getViews());
        mangaDTO.setLikes(manga.getLikes());
        mangaDTO.setRating(manga.getRating());
        mangaDTO.setBookmarks(manga.getBookmarks());
        mangaDTO.setEGenre(manga.getEGenre());
        mangaDTO.setECountry(manga.getECountry());
        mangaDTO.setEContentRating(manga.getEContentRating());
        mangaDTO.setStatus(manga.getStatus());
        mangaDTO.setCreatedAt(manga.getCreatedAt());

        MangaUserDTO mangaUserDTO = new MangaUserDTO();
        User user = manga.getUser();
        if (user != null) {
            mangaUserDTO.setUserId(user.getUserId());
            mangaUserDTO.setUsername(user.getUsername());
        }
        mangaDTO.setUser(mangaUserDTO);

        return mangaDTO;
    }
}
