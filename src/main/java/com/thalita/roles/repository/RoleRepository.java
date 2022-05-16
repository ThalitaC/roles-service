package com.thalita.roles.repository;

import com.thalita.roles.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<Object> findByName(String name);

    boolean existsByName(String roleName);
}