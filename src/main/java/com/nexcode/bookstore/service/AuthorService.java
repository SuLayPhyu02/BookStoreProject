package com.nexcode.bookstore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexcode.bookstore.mapper.AuthorMapper;
import com.nexcode.bookstore.mapper.BookMapper;
import com.nexcode.bookstore.mapper.CategoryMapper;
import com.nexcode.bookstore.models.dto.AuthorDto;
import com.nexcode.bookstore.models.dto.BookDto;
import com.nexcode.bookstore.models.dto.CategoryDto;
import com.nexcode.bookstore.models.entities.Author;
import com.nexcode.bookstore.models.entities.Book;
import com.nexcode.bookstore.repository.AuthorRepository;

@Service
public class AuthorService {

	
	private final AuthorRepository authorRepository;
	private final AuthorMapper authorMapper;
	private final BookMapper bookMapper;
	private final CategoryMapper categoryMapper;
	

	public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper, BookMapper bookMapper,
			CategoryMapper categoryMapper) {
		this.authorRepository = authorRepository;
		this.authorMapper = authorMapper;
		this.bookMapper = bookMapper;
		this.categoryMapper = categoryMapper;
	}

	public AuthorDto addAuthor(AuthorDto authorDto) {
		Author author = new Author();
		if (authorDto == null) {
			return null;
		}
		author.setName(authorDto.getName());
		authorRepository.save(author);
		authorDto.setId(author.getAuthorId());
		return authorDto;
	}

	public List<AuthorDto> getAllAuthors() {
		List<Author> authors = authorRepository.findAll();
		List<AuthorDto> authorDtos = authors.stream().map(a -> authorMapper.toDto(a)).collect(Collectors.toList());
		return authorDtos;
	}

	public AuthorDto getAuthor(Long id) {
		List<Author> authors = authorRepository.findAll();
		for (Author author : authors) {
			if (author.getAuthorId().equals(id)) {
				return authorMapper.toDto(author);
			}
		}
		return null;
	}

	@Transactional
	public AuthorDto updateAuthor(Long id, AuthorDto updateDto) {
		Author existingAuthor = authorRepository.findById(id).orElse(null);

		if (existingAuthor == null) {
			return null;
		}

		existingAuthor.setName(updateDto.getName());

		return authorMapper.toDto(existingAuthor);
	}

	public void deleteAuthor(Long id) {
		Author author = authorRepository.findById(id).orElse(null);
		authorRepository.delete(author);

	}

	public void deleteAllAuthor() {
		authorRepository.deleteAll();

	}

	// extra function
	public List<BookDto> getBooksCategoryByAuthorId(Long authorId) {
	    List<Book> books = authorRepository.findBookByAuthorIdWithCategory(authorId);
	    
	    return books.stream()
	            .map(book -> {
	                BookDto bookDto = bookMapper.toDto(book);
	                CategoryDto categoryDto=categoryMapper.toDto(book.getCategory());
	                bookDto.setCategory(categoryDto);
	                return bookDto;
	            })
	            .collect(Collectors.toList());
	} 
}
