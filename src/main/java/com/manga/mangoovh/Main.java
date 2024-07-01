package com.manga.mangoovh;

import com.manga.mangoovh.DTO.MangaDTO;

public class Main {
    public static void main(String[] args) throws Exception {
        String jsonString = "{\"title\":\"My New Manga\",\"description\":\"This is a new manga description\",\"EGenre\":\"ACTION\",\"ECountry\":\"CHINA\",\"EContentRating\":\"TEEN_16_PLUS\",\"status\":\"COMPLETED\"}";

        JsonParser jsonParser = new JsonParser();
        MangaDTO mangaDTO = jsonParser.parseJson(jsonString);

        // Access the fields of mangaDTO
        System.out.println("Title: " + mangaDTO.getTitle());
        System.out.println("Description: " + mangaDTO.getDescription());
        System.out.println("Genre: " + mangaDTO.getEgenre());
        System.out.println("Country: " + mangaDTO.getEcountry());
        System.out.println("Content Rating: " + mangaDTO.getEcontentRating());
        System.out.println("Status: " + mangaDTO.getStatus());
    }
}