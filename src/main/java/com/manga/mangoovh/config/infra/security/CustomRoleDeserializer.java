package com.manga.mangoovh.config.infra.security;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.manga.mangoovh.model.enums.ERole;
import com.manga.mangoovh.model.Role;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CustomRoleDeserializer extends JsonDeserializer<Set<Role>> {
    @Override
    public Set<Role> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Set<Role> roles = new HashSet<>();
        String[] roleNames = p.readValueAs(String[].class);

        for (String roleName : roleNames) {
            roles.add(new Role(ERole.valueOf(roleName)));
        }
        return roles;
    }
}