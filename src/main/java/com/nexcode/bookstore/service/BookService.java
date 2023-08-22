package com.nexcode.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nexcode.bookstore.mapper.AuthorMapper;
import com.nexcode.bookstore.mapper.BookMapper;
import com.nexcode.bookstore.models.dto.AuthorDto;
import com.nexcode.bookstore.models.dto.BookDto;
import com.nexcode.bookstore.models.entities.Author;
import com.nexcode.bookstore.models.entities.Book;
import com.nexcode.bookstore.models.entities.Category;
import com.nexcode.bookstore.repository.AuthorRepository;
import com.nexcode.bookstore.repository.BookRepository;
import com.nexcode.bookstore.repository.CategoryRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;
	private final BookMapper bookMapper;
	private final AuthorMapper authorMapper;
	private final AuthorRepository authorRepository;
	private final CategoryRepository categoryRepository;

	public BookService(BookRepository bookRepository, BookMapper bookMapper, AuthorMapper authorMapper,
			AuthorRepository authorRepository, CategoryRepository categoryRepository) {
		this.bookRepository = bookRepository;
		this.bookMapper = bookMapper;
		this.authorMapper = authorMapper;
		this.authorRepository = authorRepository;
		this.categoryRepository = categoryRepository;
	}

	public BookDto addBookToDatabase(BookDto bookdto) {
		Book book = new Book();
		book.setName(bookdto.getName());
		book.setPrice(bookdto.getPrice());
		bookRepository.save(book);
		bookdto.setId(book.getBookId());
		return bookdto;
	}

	public List<BookDto> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		List<BookDto> bookdtos = books.stream().map(b -> bookMapper.toDto(b)).collect(Collectors.toList());
		return bookdtos;
	}

	public BookDto getBook(Long id) {
		Book book = bookRepository.findById(id).orElse(null);
		return bookMapper.toDto(book);
	}

	public void deleteBook(Long id) {
		Book book = bookRepository.findById(id).orElse(null);
		bookRepository.delete(book);

	}

	public void deleteAllBooks() {
		bookRepository.deleteAll();

	}

	// extra functions
	public List<AuthorDto> getAuthorsByBookId(Long bookId) {
		if (bookId == null) {
			return null;
		}
		List<Author> authors = bookRepository.findAuthorsByBookId(bookId);
		List<AuthorDto> dtolist = authors.stream().map(a -> authorMapper.toDto(a)).collect(Collectors.toList());
		return dtolist;
	}

	public List<BookDto> getBookWithAuthorCategory(Long bookId) {
		List<Book> bookList = bookRepository.findAll();
		for (Book b : bookList) {
			if (b.getBookId() == bookId) {
				bookMapper.toDto(b);
			}
		}
		return null;
	}

	public BookDto saveBook(BookDto bookDto) {
		Book book = new Book();
		book.setName(bookDto.getName());
		book.setPrice(bookDto.getPrice());
		List<Author> authors = new ArrayList<>();
		List<Long> authorIds = bookDto.getAuthorIds();
		for (Long authorId : authorIds) {
			Author author = authorRepository.findById(authorId).orElse(null);
			authors.add(author);
		}
		book.setAuthors(authors);
		Long categoryId = bookDto.getCategoryId();
		System.out.println("this is categoryId " + categoryId);
		Category category = categoryRepository.findById(categoryId).orElse(null);
		book.setCategory(category);
		bookDto.setCategoryId(book.getCategory().getCategoryId());

		Book savedBook = bookRepository.save(book);
		return bookMapper.toDto(savedBook);

	}

	public BookDto getBookById(Long bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		Book book = optionalBook.get();
		return bookMapper.toDto(book);
	}

	public BookDto updatingBook(Long id, BookDto updateDto) {
		Book book = bookRepository.findById(id).orElse(null);

		if (book == null) {
			return null;
		}
		//adding existing author id into existingAuthorIds
		List<Author> existingAuthorList = book.getAuthors();
		List<Long> existingAuthorIds = new ArrayList<>();
		for (Author a : existingAuthorList) {
			Long aId = a.getAuthorId();
			existingAuthorIds.add(aId);
		}
		// updated author Long list
		List<Long> updateAuthorIdList = updateDto.getAuthorIds();
		
		for(Long aId:updateAuthorIdList)
		{
			if(!existingAuthorIds.contains(aId))
			{
				Author author=authorRepository.findById(aId).orElse(null);
				existingAuthorList.add(author);
			}
		}
		for(Long existId:existingAuthorIds)
		{
			if(!updateAuthorIdList.contains(existId))
			{
				Author author=authorRepository.findById(existId).orElse(null);
				existingAuthorList.remove(author);
			}
		}
		book.setAuthors(existingAuthorList);
		// Update other book properties
		book.setName(updateDto.getName());
		book.setPrice(updateDto.getPrice());
		// Save the updated book
		Book savedBook = bookRepository.save(book);
		return bookMapper.toDto(savedBook);
	}

}
