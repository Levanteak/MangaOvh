package com.manga.mangoovh.service;

import com.manga.mangoovh.DTO.MangaDTO;
import com.manga.mangoovh.DTO.UserAvatarDTO;
import com.manga.mangoovh.DTO.UserDTO;
import com.manga.mangoovh.DTO.UserToMangaDTO;
import com.manga.mangoovh.model.Avatar;
import com.manga.mangoovh.model.Manga;
import com.manga.mangoovh.model.User;
import com.manga.mangoovh.repository.MangaRepository;
import com.manga.mangoovh.repository.UserRepository;
import com.manga.mangoovh.service.Impl.ImplUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserService implements ImplUser {

    private final UserRepository userRepository;
    private final MangaRepository mangaRepository;

    public UserService(UserRepository userRepository, MangaRepository mangaRepository) {
        this.userRepository = userRepository;
        this.mangaRepository = mangaRepository;
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
        return userOptional.map(user -> {
            UserDTO userDTO = convertToUserDTO(user);

            userDTO.setMangaIds(user.getMangas().stream()
                    .map(Manga::getId)
                    .collect(Collectors.toSet())); // Collect to Set
            return userDTO;
        });
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

    @Transactional
    public MangaDTO addMangaToUser(Long userId, MangaDTO mangaDTO) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Manga manga = new Manga();
            manga.setTitle(mangaDTO.getTitle());
            manga.setDescription(mangaDTO.getDescription());
            manga.setViews(0);
            manga.setLikes(0);
            manga.setRating(0.0);
            manga.setBookmarks(0);
            manga.setEGenre(mangaDTO.getEgenre());
            manga.setECountry(mangaDTO.getEcountry());
            manga.setEContentRating(mangaDTO.getEcontentRating());
            manga.setStatus(mangaDTO.getStatus());
            manga.setUser(user);
            mangaRepository.save(manga);
            user = userRepository.findById(userId).get();
            MangaDTO mangaDTOResult = new MangaDTO();
            mangaDTOResult.setId(manga.getId());
            mangaDTOResult.setTitle(manga.getTitle());
            mangaDTOResult.setDescription(manga.getDescription());
            mangaDTOResult.setViews(manga.getViews());
            mangaDTOResult.setLikes(manga.getLikes());
            mangaDTOResult.setRating(manga.getRating());
            mangaDTOResult.setBookmarks(manga.getBookmarks());
            mangaDTOResult.setEgenre(manga.getEGenre());
            mangaDTOResult.setEcountry(manga.getECountry());
            mangaDTOResult.setEcontentRating(manga.getEContentRating());
            mangaDTOResult.setStatus(manga.getStatus());
            mangaDTOResult.setCreatedAt(manga.getCreatedAt());

            UserToMangaDTO userDTO = new UserToMangaDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setDescription(user.getDescription());
            userDTO.setDateCreate(user.getDateCreate());
            userDTO.setRating(user.getRating());
            userDTO.setMangas(user.getMangas().stream().map(m -> {
                MangaDTO mDto = new MangaDTO();
                mDto.setId(m.getId());
                mDto.setTitle(m.getTitle());
                mDto.setDescription(m.getDescription());
                mDto.setViews(m.getViews());
                mDto.setLikes(m.getLikes());
                mDto.setRating(m.getRating());
                mDto.setBookmarks(m.getBookmarks());
                mDto.setEgenre(m.getEGenre());
                mDto.setEcountry(m.getECountry());
                mDto.setEcontentRating(m.getEContentRating());
                mDto.setStatus(m.getStatus());
                mDto.setCreatedAt(m.getCreatedAt());
                return mDto;
            }).collect(Collectors.toSet()));

            mangaDTOResult.setUser(userDTO);
            log.info("Manga added: " + manga);
            return mangaDTOResult;
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
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
        // You can optionally set other fields like folderIds, forumIds, commentIds, etc.
        return userDTO;
    }
}