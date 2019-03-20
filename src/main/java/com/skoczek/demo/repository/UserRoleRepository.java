package com.skoczek.demo.repository;

import com.skoczek.demo.model.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRole(String role);
}
