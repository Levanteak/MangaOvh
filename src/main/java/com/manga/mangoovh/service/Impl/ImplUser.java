package com.manga.mangoovh.service.Impl;

import com.manga.mangoovh.DTO.UserDTO;

import java.util.List;
import java.util.Optional;

public interface ImplUser {
    void updateExistingUser(UserDTO existingUser, UserDTO newUserDTO);
    List<UserDTO> getAllUsersDTO();
    void deleteUser(long userId);
    Optional<UserDTO> getUserDTOByUsername(String username);
}
