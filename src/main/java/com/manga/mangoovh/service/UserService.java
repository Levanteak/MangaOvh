package com.manga.mangoovh.service;

import com.manga.mangoovh.DTO.MangaUserDTO;
import com.manga.mangoovh.DTO.UserDTO;
import com.manga.mangoovh.model.Avatar;
import com.manga.mangoovh.model.User;
import com.manga.mangoovh.repository.UserRepository;
import com.manga.mangoovh.service.Impl.ImplUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@Slf4j
public class UserService implements ImplUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDTO updateUserProfile(Long userId, UserDTO updatedUserDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Обновляем данные пользователя
        user.setUsername(updatedUserDTO.getUsername());
        user.setEmail(updatedUserDTO.getEmail());
        user.setDescription(updatedUserDTO.getDescription());

        // Обновляем или создаем новый аватар, если он предоставлен
        MultipartFile avatarFile = updatedUserDTO.getAvatar();
        if (avatarFile != null && !avatarFile.isEmpty()) {
            try {
                Avatar avatar = user.getAvatar();
                if (avatar == null) {
                    avatar = new Avatar();
                }
                avatar.setAvatar(avatarFile.getBytes());
                avatar.setUser(user);
                user.setAvatar(avatar);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save avatar for user with id: " + userId);
            }
        }

        User updatedUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(updatedUser.getUserId());
        userDTO.setUsername(updatedUser.getUsername());
        userDTO.setEmail(updatedUser.getEmail());
        userDTO.setDescription(updatedUser.getDescription());

        Avatar avatar = updatedUser.getAvatar();
        if (avatar != null) {
            String avatarBase64 = Base64.getEncoder().encodeToString(avatar.getAvatar());
            userDTO.setAvatarBase64(avatarBase64);
        }

        return userDTO;
    }
    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

}
