package com.example.origin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.origin.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository< VerificationToken, Integer> {
    public VerificationToken findByToken(String token);

}
