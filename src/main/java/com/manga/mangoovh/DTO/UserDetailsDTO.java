package com.manga.mangoovh.DTO;

import com.manga.mangoovh.model.Avatar;
import com.manga.mangoovh.model.Comment;
import com.manga.mangoovh.model.Folder;
import com.manga.mangoovh.model.Forum;
import com.manga.mangoovh.model.Manga;
import com.manga.mangoovh.model.Role;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserDetailsDTO {
    private Long userId;
    private String username;
    private String email;
    private String description;
    private LocalDateTime dateCreate;
    private Long rating;
    private Avatar avatar;
    private Set<Role> roles;
    private Set<Manga> mangas;
    private Set<Folder> folders;
    private Set<Forum> forums;
    private Set<Comment> comments;
}
