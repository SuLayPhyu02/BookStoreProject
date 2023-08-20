package com.nexcode.bookstore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexcode.bookstore.mapper1.AuthorMapper1;
import com.nexcode.bookstore.mapper1.BookMapper1;
import com.nexcode.bookstore.models.dto.AuthorDto;
import com.nexcode.bookstore.models.dto.BookDto;
import com.nexcode.bookstore.models.entities.Author;
import com.nexcode.bookstore.models.entities.Book;
import com.nexcode.bookstore.repository.BookRepository;
@Service
public class BookService {

	
	@Autowired
	private BookRepository bookrepository;
	@Autowired
	private AuthorMapper1 authormapper1;
	@Autowired
	private BookMapper1 bookmapper1;

	public BookDto addBookToDatabase(BookDto bookdto)
	{
		//Book book=bookmapper1.toEntity(bookdto);
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
	    
	    if (existingBook == null) {
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
	
	
	
	

	
}
