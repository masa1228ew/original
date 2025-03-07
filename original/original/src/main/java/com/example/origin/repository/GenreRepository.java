package com.example.origin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.origin.entity.Genre;
@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>{
	public Genre  getReferenceById(Integer id);
}
