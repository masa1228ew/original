package com.example.origin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.origin.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	 List<Category> findByGenreId(Integer genreId);
}
