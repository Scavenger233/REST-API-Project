package com.library.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.library.BaseIntegrationTest;
import com.library.BaseIntegrationTest;
import com.library.SprintBootCrudApplication;
import com.library.exception.BookNotFoundException;
import com.library.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SprintBootCrudApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(value = OrderAnnotation.class)
@ActiveProfiles("test")
public class BookControllerIntegrationTest extends BaseIntegrationTest {
    @LocalServerPort
    private int port;

	@Autowired
	private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    public void addBook() {

    	Book book = new Book(10001, "GoneWithTheWind", "Tomorrow is another day!");

        HttpEntity<Book> entity = new HttpEntity<>(book, getHttpHeader());

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/library/GoneWithTheWind/books"),
                HttpMethod.POST, entity, String.class);

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        assertTrue(actual.contains("/library/GoneWithTheWind/books"));

    }
    
    @Test
    @Order(2)
    public void updateBook() throws JSONException {

    	Book book = new Book(1, "GoneWithTheWind", "Tomorrow is another day! updated");

        HttpEntity<Book> entity = new HttpEntity<>(book, getHttpHeader());

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/library/GoneWithTheWind/books/1"),
                HttpMethod.PUT, entity, String.class);
        
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());

        String expected = "{\"id\":1,\"bookName\":\"GoneWithTheWind\",\"description\":\"Tomorrow is another day! updated\"}";

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }
    @WithMockUser(username = "pocky", roles = "USER")
    @Test
    @Order(3)
    public void testGetBook() throws JSONException, JsonProcessingException {

        HttpEntity<String> entity = new HttpEntity<>(null, getHttpHeader());

        ResponseEntity<String> response1 = restTemplate.exchange(
                createURLWithPort("/api/library/GoneWithTheWind/books/1"),
                HttpMethod.GET, entity, String.class);

        String expected = "{\"id\":1,\"bookName\":\"GoneWithTheWind\",\"description\":\"Tomorrow is another day! updated\"}";

        JSONAssert.assertEquals(expected, response1.getBody(), false);
        
    }
    
	@Test
	@Order(4)
	public void testDeleteBook() {
		Book book = restTemplate.getForObject(createURLWithPort("/api/library/GoneWithTheWind/books/1"), Book.class);
		assertNotNull(book);

		HttpEntity<String> entity = new HttpEntity<>(null, getHttpHeader());

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/library/GoneWithTheWind/books/1"),
                HttpMethod.DELETE, entity, String.class);
		
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCode().value());

		try {
			book = restTemplate.getForObject("/api/library/GoneWithTheWind/books/1", Book.class);
		} catch (BookNotFoundException e) {
			assertEquals("Book id not found : 1", e.getMessage());
		}
	}

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
