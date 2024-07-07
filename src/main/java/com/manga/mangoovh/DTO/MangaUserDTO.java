package com.manga.mangoovh.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MangaUserDTO {
    private Long userId;
    private String username;
}
