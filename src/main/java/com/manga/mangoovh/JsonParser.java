package com.manga.mangoovh;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manga.mangoovh.DTO.MangaDTO;

public class JsonParser {
    private ObjectMapper objectMapper = new ObjectMapper();

    public MangaDTO parseJson(String jsonString) throws Exception {
        return objectMapper.readValue(jsonString, MangaDTO.class);
    }
}
