package com.example.origin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.origin.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {	
	public User findByEmail(String email);
	public User  getReferenceById(Integer id);
//	Optional<User> findByEmail(String email);
//	public User findByName(String username);
	
	}
