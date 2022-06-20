package com.iotgqldemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class Film {
	
	@Id
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private int stock;
	
	public Film() {
		
	}
	
	public Film(int id, String title, String description, int releaseYear, int stock) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.stock = stock;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
