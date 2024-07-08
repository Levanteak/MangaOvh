package com.manga.mangoovh.service;

import com.manga.mangoovh.DTO.MangaUserDTO;
import com.manga.mangoovh.DTO.UserDTO;
import com.manga.mangoovh.DTO.UserDetailsDTO;
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
import java.util.List;
import java.util.stream.Collectors;

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

    public UserDetailsDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        return convertToUserDetailsDTO(user);
    }

    public UserDetailsDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return convertToUserDetailsDTO(user);
    }

    public List<UserDetailsDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToUserDetailsDTO).collect(Collectors.toList());
    }

    private UserDetailsDTO convertToUserDetailsDTO(User user) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setUserId(user.getUserId());
        userDetailsDTO.setUsername(user.getUsername());
        userDetailsDTO.setEmail(user.getEmail());
        userDetailsDTO.setDescription(user.getDescription());
        userDetailsDTO.setDateCreate(user.getDateCreate());
        userDetailsDTO.setRating(user.getRating());
        userDetailsDTO.setAvatar(user.getAvatar());
        userDetailsDTO.setRoles(user.getRoles());
        userDetailsDTO.setMangas(user.getMangas());
        userDetailsDTO.setFolders(user.getFolders());
        userDetailsDTO.setForums(user.getForums());
        userDetailsDTO.setComments(user.getComments());
        return userDetailsDTO;
    }
    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

}
