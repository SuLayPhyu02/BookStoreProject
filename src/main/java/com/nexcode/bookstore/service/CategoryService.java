package com.nexcode.bookstore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexcode.bookstore.mapper.implement1.BookMapperImpl1;
import com.nexcode.bookstore.mapper.implement1.CategoryMapperImp1;
import com.nexcode.bookstore.models.dto.BookDto;
import com.nexcode.bookstore.models.dto.CategoryDto;
import com.nexcode.bookstore.models.entities.Book;
import com.nexcode.bookstore.models.entities.Category;
import com.nexcode.bookstore.repository.BookRepository;
import com.nexcode.bookstore.repository.CategoryRepository;
@Service
public class CategoryService {
	@Autowired
	private CategoryMapperImp1 categoryMapper1;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired 
	private BookRepository bookRepository;

	@Autowired
	private BookMapperImpl1 bookMapper1;
	//normal crud
	public void saveCategory(CategoryDto categoryDto)
	{
		Category category=new Category();
		category.setName(categoryDto.getName());
		categoryRepository.save(category);
	}
	public List<CategoryDto> getAllCategory(){
		List<Category> category=categoryRepository.findAll();
		List<CategoryDto> catList=category.stream().map(c->categoryMapper1.toDto(c)).collect(Collectors.toList());
		return catList;
	}
	public CategoryDto getCategory(Long id)
	{
	    List<Category> categories = categoryRepository.findAll();
	    for (Category category : categories)
	    {
	        if (category.getCategoryId().equals(id))
	        {
	            return categoryMapper1.toDto(category);
	        }
	    }
	    return null;
	}
	@Transactional
	public CategoryDto updateCategory(Long id, CategoryDto updateDto)
	{
	    Category existingCategory = categoryRepository.findById(id).orElse(null);
	    
	    if (existingCategory == null) {
	        return null; 
	    }
	    
	    existingCategory.setName(updateDto.getName());
	    
	    return categoryMapper1.toDto(existingCategory);
	}
	public void deleteCategory(Long id)
	{
		Category category=categoryRepository.findById(id).orElse(null);
		categoryRepository.delete(category);
		
	}
	//extra added 
	public BookDto addBookWithCategory(Long id,BookDto bookDto)
	{
		Category category=categoryRepository.findById(id).orElse(null);
		if(category!=null)
		{
			Book book=new Book();
			book.setName(bookDto.getName());
			book.setPrice(bookDto.getPrice());
			book.setCategory(category);
			Book savedBook=bookRepository.save(book);
			return bookMapper1.toDto(savedBook);
		}
		else {
			return null;
		}
		
	}
	public void deleteAllCategories() {
		categoryRepository.deleteAll();
		
	}
	//extra function
	public List<BookDto> getBooksByCategories(Long id) {
	    List<Book> bookList = categoryRepository.findBooksByCategoryId(id);
	    
	    return bookList.stream()
	            .map(book -> {
	                BookDto bookDto = bookMapper1.toDto(book);
	                
	                CategoryDto categoryDto = categoryMapper1.toDto(book.getCategory());
	                bookDto.setCategory(categoryDto);
	                
	                return bookDto;
	            })
	            .collect(Collectors.toList());
	}



}
