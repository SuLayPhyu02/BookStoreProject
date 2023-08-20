package com.nexcode.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexcode.bookstore.models.entities.Author;
import com.nexcode.bookstore.models.entities.Book;

public interface AuthorRepository extends JpaRepository<Author, Long>{

	@Query("SELECT b FROM Book b JOIN b.authors a JOIN FETCH b.category c WHERE a.authorId = :authorId")
	List<Book> findBookByAuthorIdWithCategory(@Param("authorId") Long authorId);

}
