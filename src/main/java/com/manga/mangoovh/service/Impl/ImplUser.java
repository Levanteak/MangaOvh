package com.manga.mangoovh.service.Impl;

import com.manga.mangoovh.model.User;

import java.util.List;
import java.util.Optional;

public interface ImplUser {
    void updateExistingUser(User existingUser, User newUser);
    List<User> getAllUsers();
    void deleteUser(long userId);
    Optional<User> getUserByUsername (String username);
}
