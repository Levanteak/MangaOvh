package com.manga.mangoovh.service.Impl;

import com.manga.mangoovh.DTO.MangaDTO;
import com.manga.mangoovh.DTO.UserDTO;
import com.manga.mangoovh.model.Manga;

import java.util.List;
import java.util.Optional;

public interface ImplUser {
    void updateExistingUser(UserDTO existingUser, UserDTO newUserDTO);
    List<UserDTO> getAllUsersDTO();
    void deleteUser(long userId);
    Optional<UserDTO> getUserDTOByUsername(String username);
    void addAvatarToUser(Long userId, byte[] avatar);
    MangaDTO addMangaToUser(Long userId, MangaDTO mangaDTO);
}
