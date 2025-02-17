package com.example.origin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.origin.entity.Collection;
import com.example.origin.entity.Genre;
import com.example.origin.entity.User;
import com.example.origin.form.CollectionEditForm;
import com.example.origin.form.CollectionRegisterForm;
import com.example.origin.repository.CollectionRepository;
import com.example.origin.repository.CustomCollectionRepository;
import com.example.origin.repository.GenreRepository;

import jakarta.transaction.Transactional;

@Service
public class CollectionService {
	private final CollectionRepository collectionRepository;
	private final CustomCollectionRepository customCollectionRepository;
	private final GenreRepository genreRepository;
	public CollectionService(CollectionRepository collectionRepository,GenreRepository genreRepository
								,CustomCollectionRepository customCollectionRepository) {
		this.collectionRepository = collectionRepository;
		this.customCollectionRepository = customCollectionRepository;
		this.genreRepository = genreRepository;
	}
	
	@Transactional
	public void create(CollectionRegisterForm collectionRegisterForm,User user) {
		Collection collection = new Collection();
		Genre genre = genreRepository.getReferenceById(collectionRegisterForm.getGenre());
	collection.setName(collectionRegisterForm.getName());
	collection.setGenre(genre);
	collection.setUser(user);
	
	collectionRepository.save(collection);
}
	
	 @Transactional
	    public void update(CollectionEditForm collectionEditForm) {
		 Collection collection = collectionRepository.getReferenceById(collectionEditForm.getId());
//		 Genre genre = genreRepository.getReferenceById(collectionEditForm.getGenreId().getId());
	        
		 collection.setName(collectionEditForm.getName());                
//		 collection.setGenre(genre);
			
	                    
			collectionRepository.save(collection);
	    }    
	
	
	public List<Collection>findAll(String sort_fg){
		List<Collection>collectionList = customCollectionRepository.findAllCollections(sort_fg);
		if(collectionList.size()==0) {
			collectionList=null;
		}
		return collectionList;
	}
	public Map<String,String> createMap(){
		Map<String,String>map = new HashMap();
		map.put("1","更新順");
		map.put("2", "五十音順");
		
		return map;
	}
	
	 public Genre getGenreIdByCollection(Integer collectionId) {
	        return collectionRepository.findById(collectionId)
	                .orElseThrow(() -> new RuntimeException("Collection not found"))
	                .getGenre();
	    }
	  

	    
}