package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entities.User;



public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
