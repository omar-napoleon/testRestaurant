package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
