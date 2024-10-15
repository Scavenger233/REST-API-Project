package com.library.service;

import java.util.List;

import com.library.model.Book;

public interface BookService {

	List<Book> getAllBooks(String bookName);
	
	Book getBook(String bookName, long id);
	
	void deleteBook(String bookName, long id);
	
	Book updateBook(String bookName, long id, Book book);
	
	Book createBook(String bookName, Book book);
}
