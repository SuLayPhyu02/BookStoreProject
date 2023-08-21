package com.nexcode.bookstore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexcode.bookstore.mapper.AuthorMapper;
import com.nexcode.bookstore.mapper.BookMapper;
import com.nexcode.bookstore.models.dto.AuthorDto;
import com.nexcode.bookstore.models.dto.BookDto;
import com.nexcode.bookstore.models.requests.BookRequest;
import com.nexcode.bookstore.models.response.AuthorResponse;
import com.nexcode.bookstore.models.response.BookResponse;
import com.nexcode.bookstore.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookMapper bookMapper1;
	private final BookService bookservice;
	private final AuthorMapper authorMapper1;

	public BookController(BookMapper bookMapper1, BookService bookservice, AuthorMapper authorMapper1) {
		super();
		this.bookMapper1 = bookMapper1;
		this.bookservice = bookservice;
		this.authorMapper1 = authorMapper1;
	}

//	@PostMapping
//	public BookDto createBook(@RequestBody BookRequest request) {
//		BookDto dto = bookMapper1.toDto(request);
//		BookDto createdBookDto = bookservice.addBookToDatabase(dto);
//		return createdBookDto; 
//	}

	@GetMapping
	public List<BookResponse> getAllBooks() {
		List<BookDto> dtolist = bookservice.getAllBooks();
		List<BookResponse> responseList = dtolist.stream().map(r -> bookMapper1.toResponse(r))
				.collect(Collectors.toList());
		return responseList;
	}

//	@GetMapping("/{id}")
//	public BookDto getBook(@PathVariable Long id) {
//		return bookservice.getBook(id);
//	}

	@PutMapping("/{id}")
	public BookDto updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
		BookDto updateBook = bookMapper1.toDto(request);
		BookDto alreadyUpdatedDto = bookservice.updateBook(id, updateBook);
		return alreadyUpdatedDto;
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookservice.deleteBook(id);
	}

	@DeleteMapping
	public void deleteAll() {
		bookservice.deleteAllBooks();
	}

	// extra function
	@GetMapping("/{bookId}/authors")
	public List<AuthorResponse> getAuthorsByBook(@PathVariable Long bookId) {
		List<AuthorDto> authorDto = bookservice.getAuthorsByBookId(bookId);
		List<AuthorResponse> responses = authorDto.stream().map(a -> authorMapper1.toResponse(a))
				.collect(Collectors.toList());
		return responses;
	}

	@GetMapping("/{id}")
	public BookResponse getAll(@PathVariable Long id) {
		BookDto dto = bookservice.getBookById(id);
		BookResponse response = bookMapper1.toResponse(dto);
		return response;
	}

	@PostMapping
	public BookDto addBookWithCategoryAuthors(@RequestBody BookRequest request) {
		BookDto dto = bookMapper1.toDto(request);
		BookDto createdBookDto = bookservice.saveBook(dto);
		return createdBookDto;
	}

}
