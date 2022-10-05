package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.Role;


public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
