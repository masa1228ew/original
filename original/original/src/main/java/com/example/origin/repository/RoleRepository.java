package com.example.origin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.origin.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByName(String name);    
}
