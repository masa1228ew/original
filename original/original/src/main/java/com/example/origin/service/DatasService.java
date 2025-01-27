package com.example.origin.service;

import java.util.List;

import com.example.origin.entity.Collection;
import com.example.origin.entity.Datas;
import com.example.origin.repository.DatasRepository;


public class DatasService {

	private final DatasRepository datasRepository;
	private final GenreService genreService;
	
	public DatasService(DatasRepository datasRepository
						,GenreService genreService) {
		this.datasRepository = datasRepository;
		this.genreService = genreService;
	}
	
	public List<Datas> getAllData() {
        return datasRepository.findAll();
    }
	
	public List<?> getGenresForData(Datas datas) {
        Collection collection = datas.getCollection();
        if (collection == null) {
            throw new IllegalArgumentException("Data does not have a valid collection.");
        }
        return genreService.getGenresByCollection(collection);
    }
	 public void saveData(Datas datas) {
	        datasRepository.save(datas);
	    }
}
