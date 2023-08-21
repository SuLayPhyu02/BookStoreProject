package com.nexcode.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	
	private final BookRepository bookrepository;
	private final BookMapper bookmapper1;
	private final AuthorMapper authormapper1;
	private final AuthorRepository authorrepository;
	private final CategoryRepository categoryrepository;

	
	
	public BookService(BookRepository bookrepository, BookMapper bookmapper1, AuthorMapper authormapper1,
			AuthorRepository authorrepository, CategoryRepository categoryrepository) {
		super();
		this.bookrepository = bookrepository;
		this.bookmapper1 = bookmapper1;
		this.authormapper1 = authormapper1;
		this.authorrepository = authorrepository;
		this.categoryrepository = categoryrepository;
	}
	public BookDto addBookToDatabase(BookDto bookdto)
	{
		Book book=new Book();
		book.setName(bookdto.getName());
		book.setPrice(bookdto.getPrice());
		bookrepository.save(book);
		bookdto.setId(book.getBookId());
		return bookdto;
	}
	public List<BookDto> getAllBooks()
	{
		List<Book> books=bookrepository.findAll();
		List<BookDto> bookdtos=books.stream().map(b->bookmapper1.toDto(b)).collect(Collectors.toList());
		return bookdtos;
	}
	public BookDto getBook(Long id)
	{
	    List<Book> books = bookrepository.findAll();
	    for (Book book : books)
	    {
	        if (book.getBookId().equals(id))
	        {
	            return bookmapper1.toDto(book);
	        }
	    }
	    return null;
	}
	@Transactional
	public BookDto updateBook(Long id, BookDto updateDto)
	{
	    Book existingBook = bookrepository.findById(id).orElse(null);
	    BookDto existingBookDto=bookmapper1.toDto(existingBook);
	    if (existingBookDto == null) 
	    {
	        return null; 
	    }
	    List<AuthorDto>existingAuthorDtos=existingBookDto.getAuthordtos();
	    List<Author>newAuthor=new ArrayList<>();
	    for(AuthorDto authorId:updateDto.getAuthordtos())
	    {
	    	Author author=authorrepository.findById(id).orElse(null);
	    	//AuthorDto dto=authormapper1.toDto(authorDto);
	    	if(author==null)
	    	{
	    		return null;
	    	}
	    	else
	    	{
	    		newAuthor.add(author);
	    	}
	    }
	    existingBook.setAuthors(newAuthor);
	    Category newCatgory=categoryrepository.findById(updateDto.getCategoryId()).orElse(null);
	    if (newCatgory != null) {
	        existingBook.setCategory(newCatgory);
	    } else {
	        return null;
	    }
	    existingBook.setName(updateDto.getName());
	    existingBook.setPrice(updateDto.getPrice());
	    
	    return bookmapper1.toDto(existingBook);
	}
	public void deleteBook(Long id)
	{
		Book book=bookrepository.findById(id).orElse(null);
		bookrepository.delete(book);
		
	}
	public void deleteAllBooks() {
		bookrepository.deleteAll();
		
	}
	//extra functions
	public List<AuthorDto> getAuthorsByBookId(Long bookId) {
		if(bookId==null)
		{
			return null;
		}
		List<Author>authors=bookrepository.findAuthorsByBookId(bookId);
		List<AuthorDto>dtolist=authors.stream().map(a->authormapper1.toDto(a)).collect(Collectors.toList());
		return dtolist;
	}
	public List<BookDto> getBookWithAuthorCategory(Long bookId) {
		List<Book> bookList=bookrepository.findAll();
		for(Book b:bookList)
		{
			if(b.getBookId()==bookId)
			{
				bookmapper1.toDto(b);
			}
		}
		return null;
	}
	public BookDto saveBook(BookDto bookDto)
	{
		Book book=new Book();
		book.setName(bookDto.getName());
		book.setPrice(bookDto.getPrice());
		List<Author>authors=new ArrayList<>();
		List<Long>authorIds=bookDto.getAuthorIds();
		for(Long authorId:authorIds)
		{
			Author author=authorrepository.findById(authorId).orElse(null);
			authors.add(author);
		}
		book.setAuthors(authors);
		Long categoryId=bookDto.getCategoryId();
		System.out.println("this is categoryId "+categoryId);
		Category category=categoryrepository.findById(categoryId).orElse(null);
		book.setCategory(category);
		bookDto.setCategoryId(book.getCategory().getCategoryId());
		
		Book savedBook=bookrepository.save(book);
		return bookmapper1.toDto(savedBook);
		
	}
	public BookDto getBookById(Long bookId) {
		Optional<Book> optionalBook = bookrepository.findById(bookId);
		Book book = optionalBook.get();
		return bookmapper1.toDto(book);
	}
	
	
	
	

	
}
