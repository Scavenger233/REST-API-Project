package com.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.library.exception.BookNotFoundException;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.impl.BookServiceImpl;

@ExtendWith(SpringExtension.class)
public class BookServiceMockTest {
	
	@Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService = new BookServiceImpl();

    @Test
    public void getAllBooks() {
    	List<Book> books = Arrays.asList(
                new Book(10001, "pocky", "Learn Java"),
                new Book(10002, "pocky", "Learn Spring Boot"));
    	
    	when(bookRepository.findAll()).thenReturn(books);
		assertEquals(books, bookService.getAllBooks("in28minutes"));
    }
    
    @Test
    public void getBook() {
    	Book book = new Book(10001, "pocky", "Learn Java");
    	
    	when(bookRepository.findById(Long.valueOf(10001))).thenReturn(Optional.of(book));
		assertEquals(book, bookService.getBook("pocky", Long.valueOf(10001)));
    }
    
    @Test
    public void getBookNotFound() {
    	
    	BookNotFoundException exception = assertThrows(
    			BookNotFoundException.class,
    	           () -> bookService.getBook("pocky", Long.valueOf(10001)),
    	           "Book id not found : 10001"
    	    );

    	    assertEquals("Book id not found : 10001", exception.getMessage());
    }
    
    @Test
    public void deleteBook() {
    	
    	Book book = new Book(10001, "pocky", "Learn Java");
    	
    	when(bookRepository.findById(Long.valueOf(10001))).thenReturn(Optional.of(book));
    	bookService.deleteBook("pocky", Long.valueOf(10001));

		verify(bookRepository, times(1)).deleteById(Long.valueOf(10001));
    }
    
    @Test
    public void updateBook() {
    	Book book = new Book(10001, "in28minutes", "Learn Java");
    	
    	when(bookRepository.save(book)).thenReturn(book);
		assertEquals(book, bookService.updateBook("pocky", Long.valueOf(10001), book));
    }
    
    @Test
    public void createBook() {
    	Book book = new Book(10001, "pocky", "Learn Java");
    	
    	when(bookRepository.save(book)).thenReturn(book);
		assertEquals(book, bookService.createBook("pocky", book));

    }

}
