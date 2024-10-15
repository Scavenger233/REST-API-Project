package com.library.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue
	private Long id;
	private String bookName;
	@com.fasterxml.jackson.annotation.JsonProperty("description")
	private String bookDescription;

	public Book() {

	}

	public Book(long id, String bookName, String bookDescription) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.bookDescription = bookDescription;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

}