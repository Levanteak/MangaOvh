package com.manga.mangoovh.service.Impl;

import com.manga.mangoovh.DTO.MangaDTO;
import com.manga.mangoovh.model.Manga;

public interface ImplMangaService {
    MangaDTO addMangaToUser(Long userId, Manga mangaRequest);
    MangaDTO getMangaById(Long mangaId);
    MangaDTO updateManga(Long mangaId, Manga mangaRequest);
}
