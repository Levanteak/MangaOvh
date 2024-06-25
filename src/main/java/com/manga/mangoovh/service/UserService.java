package com.manga.mangoovh.service;

import com.manga.mangoovh.model.User;
import com.manga.mangoovh.repository.UserRepository;
import com.manga.mangoovh.service.Impl.ImplUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ImplUser {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void updateExistingUser(User existingUser, User newUser){
        existingUser.setUsername(newUser.getUsername());
        existingUser.setDescription(newUser.getDescription());
        existingUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        existingUser.setUsername(newUser.getUsername());
        existingUser.setEmail(newUser.getEmail());
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(long userId){
        userRepository.deleteById(userId);
    }

    public Optional<User> getUserByUsername (String username){
        return userRepository.findByUsername(username);
    }

}
