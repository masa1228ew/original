package com.example.origin.service;

import org.springframework.stereotype.Service;

import com.example.origin.repository.GenreRepository;

@Service
public class GenreService {
private final GenreRepository genreRepository;


public GenreService(GenreRepository genreRepository
					) {
	this.genreRepository = genreRepository;
	
}
	

}

