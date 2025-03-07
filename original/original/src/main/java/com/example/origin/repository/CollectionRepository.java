package com.example.origin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.origin.entity.Collection;
import com.example.origin.entity.User;
@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer>{
	 public Page<Collection> findAll(Pageable pageable);
	   Page<Collection> findByUser(User user, Pageable pageable);
public Page<Collection> findAllByOrderByCreatedAtDesc(Pageable pageable);
public Page<Collection> findAllByOrderByNameAsc(Pageable pageable);
List<Collection> findByUser(User user);

}
