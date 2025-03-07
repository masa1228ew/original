package com.example.origin.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.origin.entity.Datas;

public interface DatasRepository extends JpaRepository<Datas, Integer>{
	public Page<Datas> findAll(Pageable pageable);
	public Page<Datas> findByNameLike(String keyword, Pageable pageable);
	Page<Datas> findByCollectionId(Integer collectionId, Pageable pageable);
	 Page<Datas> findByCollectionIdAndNameContaining(Integer collectionId, String keyword, Pageable pageable);
	 @Query(value = "SELECT * FROM datas WHERE collection_id = :collectionId ORDER BY RAND() LIMIT 1", nativeQuery = true)
	    Optional<Datas> findRandomDataByCollectionId(@Param("collectionId") Integer collectionId);
	 @Query("SELECT SUM(d.price) FROM Datas d WHERE d.collection.id = :collectionId")
	    Integer getTotalPriceByCollectionId(@Param("collectionId") Integer collectionId);
	 @Query("SELECT COUNT(d) FROM Datas d WHERE d.collection.id = :collectionId")
	    Integer countByCollectionId(@Param("collectionId") Integer collectionId);
	public Page<Datas> findByCollectionIdAndCategoryIdAndNameContaining(Integer integer, Integer categoryId,
			Object object, Pageable pageable);
	
}
