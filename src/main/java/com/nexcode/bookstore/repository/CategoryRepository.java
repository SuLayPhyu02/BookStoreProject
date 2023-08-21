package com.nexcode.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexcode.bookstore.models.entities.Book;
import com.nexcode.bookstore.models.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("SELECT b FROM Book b JOIN b.category c WHERE c.categoryId = :categoryId")
    List<Book> findBooksByCategoryId(@Param("categoryId") Long categoryId);
	
	Category findByName(String name);

	
}
