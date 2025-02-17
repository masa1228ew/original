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
@Table(name = "collection")
@Data
public class Collection {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Integer id;
	 
	 @Column(name="name")
	 private String name;
	
	 @ManyToOne
	 @JoinColumn(name="genre_id")
	 private Genre genre;
	 
	 @ManyToOne
	 @JoinColumn(name="user_id")
	 private User user;
	 
	 @Column(name="created_at",insertable=false,updatable=false)
	 private Timestamp createdAt;

	 @Column(name="updated_at",insertable=false,updatable=false)
	 private Timestamp updatedAt;

	 @Override
	 public String toString() {
		 return "Collection [id = "+ id +",name="+name+"]";
	 }
}
