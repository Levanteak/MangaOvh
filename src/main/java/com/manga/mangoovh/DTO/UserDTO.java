package com.manga.mangoovh.DTO;

import com.manga.mangoovh.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String description;
    private LocalDateTime dateCreate;
    private Long rating;
    private Set<Role> roleIds = new HashSet<>();
    private Set<Long> mangaIds = new HashSet<>();
    private Set<Long> folderIds = new HashSet<>();
    private Set<Long> forumIds = new HashSet<>();
    private Set<Long> commentIds = new HashSet<>();
}
