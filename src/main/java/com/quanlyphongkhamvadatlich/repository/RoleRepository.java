package com.quanlyphongkhamvadatlich.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quanlyphongkhamvadatlich.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByName(String name);
    
}
