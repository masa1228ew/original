package com.example.origin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.origin.entity.Collection;
@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer>{
	 public Page<Collection> findAll(Pageable pageable);
public Page<Collection> findAllByOrderByCreatedAtDesc(Pageable pageable);
public Page<Collection> findAllByOrderByNameAsc(Pageable pageable);
Collection findByName(String name);

}
