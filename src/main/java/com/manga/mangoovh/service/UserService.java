package com.manga.mangoovh.service;

import com.manga.mangoovh.DTO.UserAvatarDTO;
import com.manga.mangoovh.DTO.UserDTO;
import com.manga.mangoovh.model.Avatar;
import com.manga.mangoovh.model.User;
import com.manga.mangoovh.repository.UserRepository;
import com.manga.mangoovh.service.Impl.ImplUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserService implements ImplUser {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void updateExistingUser(UserDTO existingUserDTO, UserDTO newUserDTO) {
        Optional<User> existingUserOptional = userRepository.findById(existingUserDTO.getUserId());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setUsername(newUserDTO.getUsername());
            existingUser.setEmail(newUserDTO.getEmail());
            existingUser.setDescription(newUserDTO.getDescription());
            existingUser.setRating(newUserDTO.getRating());
            log.info("updateExistingUser update: " +  existingUser);
            userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with ID: " + existingUserDTO.getUserId());
        }
    }

    public List<UserDTO> getAllUsersDTO() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<UserDTO> getUserDTOByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(this::convertToUserDTO);
    }
    @Transactional
    @Override
    public void addAvatarToUser(Long userId, byte[] avatar) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Avatar userAvatar = new Avatar();
            userAvatar.setAvatar(avatar);
            user.setAvatar(userAvatar);
            userAvatar.setUser(user);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }
    public Optional<UserAvatarDTO> getUserAvatarDTOByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(this::convertToUserAvatarDTO);
    }


    private UserAvatarDTO convertToUserAvatarDTO (User user){
        UserAvatarDTO userAvatarDTO = new UserAvatarDTO();
        userAvatarDTO.setAvatar(user.getAvatar());
        return userAvatarDTO;
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setDescription(user.getDescription());
        userDTO.setDateCreate(user.getDateCreate());
        userDTO.setRating(user.getRating());
        userDTO.setRoleIds(user.getRoles());
        return userDTO;
    }
}