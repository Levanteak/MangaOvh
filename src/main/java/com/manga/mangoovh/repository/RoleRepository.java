package com.manga.mangoovh.repository;

import com.manga.mangoovh.model.enums.ERole;
import com.manga.mangoovh.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}