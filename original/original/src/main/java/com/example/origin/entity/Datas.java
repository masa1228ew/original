package com.example.origin.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "datas")
@Data

public class Datas {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Integer id;
	 
	  @Column(name = "name")
	    private String name;
	  
	  @ManyToOne
	  @JoinColumn(name="collection_id", nullable = false)
	  private Collection collection;
	  
	  @ManyToOne
	  @JoinColumn(name="category_id", nullable = false)
	  private Category category;
	  
	  @Column(name="price")
	   private Integer price;
	  
	  @Column(name="created_at",insertable=false,updatable=false)
	  private Timestamp createdAt;

	  @Column(name="updated_at",insertable=false,updatable=false)
	  private Timestamp updatedAt;
	  

	
	  
	   
}
