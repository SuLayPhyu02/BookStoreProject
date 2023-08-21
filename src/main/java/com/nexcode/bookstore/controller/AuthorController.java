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
import com.nexcode.bookstore.models.dto.AuthorDto;
import com.nexcode.bookstore.models.requests.AuthorRequest;
import com.nexcode.bookstore.models.response.AuthorResponse;
import com.nexcode.bookstore.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
	
	
	private final AuthorService authorService;
	private final AuthorMapper authorMapper1;

	
	
	public AuthorController(AuthorService authorService, AuthorMapper authorMapper1) {
		super();
		this.authorService = authorService;
		this.authorMapper1 = authorMapper1;
	}

	@PostMapping
	public AuthorDto createAuthor(@RequestBody AuthorRequest request)
	{
	    AuthorDto dto = authorMapper1.toDto(request);
	    AuthorDto createdAuthorDto = authorService.addAuthor(dto);
	    return createdAuthorDto; 
	}

	@GetMapping
	public List<AuthorResponse> getAllBooks()
	{
		List<AuthorDto> dtolist=authorService.getAllAuthors();
		List<AuthorResponse> responseList=dtolist.stream().map(a->authorMapper1.toResponse(a)).collect(Collectors.toList());
		return responseList;
	}
	@GetMapping("/{id}")
	public AuthorDto getAuthor(@PathVariable Long id)
	{
		return authorService.getAuthor(id);
	}
	@PutMapping("/{id}")
	public AuthorDto updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest request)
	{
	    AuthorDto updateAuthor = authorMapper1.toDto(request);
	    AuthorDto alreadyUpdatedDto = authorService.updateAuthor(id, updateAuthor);
	    return alreadyUpdatedDto;
	}
	@DeleteMapping("/{id}")
	public void deleteAuthor(@PathVariable Long id)
	{
		authorService.deleteAuthor(id);
	}
	@DeleteMapping
	public void deleteAll()
	{
		authorService.deleteAllAuthor();
		
	}
	//extra
//	@GetMapping("/{authorId}/books")
//	public List<BookResponse> getBooksByAuthorWithCategory(@PathVariable Long authorId) {
//	    List<BookDto> dtolist = authorService.getBooksCategoryByAuthorId(authorId);
//	    List<BookResponse> responses = dtolist.stream()
//	            .map(b -> {
//	                BookResponse response = bookmapper1.toResponse(b);
//	                setCategoryResponse(b.getCategory());
//	                return response;
//	            })
//	            .collect(Collectors.toList());
//	    return responses;
//	}
	
}
