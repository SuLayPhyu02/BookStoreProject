package com.nexcode.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexcode.bookstore.models.entities.Author;
import com.nexcode.bookstore.models.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	@Query("SELECT b.authors FROM Book b WHERE b.bookId = :bookId")
	List<Author> findAuthorsByBookId(@Param("bookId") Long bookId);




	
	

}
