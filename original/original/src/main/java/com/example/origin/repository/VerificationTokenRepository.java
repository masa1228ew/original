package com.example.origin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.origin.entity.VerificationToken;
@Repository
public interface VerificationTokenRepository extends JpaRepository< VerificationToken, Integer> {
    public VerificationToken findByToken(String token);

}
