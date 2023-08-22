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

	private final BookMapper bookMapper;
	private final BookService bookService;
	private final AuthorMapper authorMapper;
	
	public BookController(BookMapper bookMapper, BookService bookService, AuthorMapper authorMapper) {
		this.bookMapper = bookMapper;
		this.bookService = bookService;
		this.authorMapper = authorMapper;
	}

	@GetMapping
	public List<BookResponse> getAllBooks() {
		List<BookDto> dtolist = bookService.getAllBooks();
		List<BookResponse> responseList = dtolist.stream().map(r -> bookMapper.toResponse(r))
				.collect(Collectors.toList());
		return responseList;
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}

	@DeleteMapping
	public void deleteAll() {
		bookService.deleteAllBooks();
	}

	// extra function
	
	@GetMapping("/{bookId}/authors")
	public List<AuthorResponse> getAuthorsByBook(@PathVariable Long bookId) {
		List<AuthorDto> authorDto = bookService.getAuthorsByBookId(bookId);
		List<AuthorResponse> responses = authorDto.stream().map(a -> authorMapper.toResponse(a))
				.collect(Collectors.toList());
		return responses;
	}

	@GetMapping("/{id}")
	public BookResponse getAll(@PathVariable Long id) {
		BookDto dto = bookService.getBookById(id);
		BookResponse response = bookMapper.toResponse(dto);
		return response;
	}

	@PostMapping
	public BookDto addBookWithCategoryAuthors(@RequestBody BookRequest request) {
		BookDto dto = bookMapper.toDto(request);
		BookDto createdBookDto = bookService.saveBook(dto);
		return createdBookDto;
	}
	
	@PutMapping("/{id}")
	public BookDto updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
		BookDto updateBook = bookMapper.toDto(request);
		BookDto alreadyUpdatedDto = bookService.updatingBook(id, updateBook);
		return alreadyUpdatedDto;
	}

}
