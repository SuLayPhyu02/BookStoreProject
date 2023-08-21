package com.nexcode.bookstore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexcode.bookstore.mapper.BookMapper;
import com.nexcode.bookstore.mapper.CategoryMapper;
import com.nexcode.bookstore.models.dto.BookDto;
import com.nexcode.bookstore.models.dto.CategoryDto;
import com.nexcode.bookstore.models.requests.BookRequest;
import com.nexcode.bookstore.models.requests.CategoryRequest;
import com.nexcode.bookstore.models.response.BookResponse;
import com.nexcode.bookstore.models.response.CategoryResponse;
import com.nexcode.bookstore.service.CategoryService;
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryMapper categoryMappper1;
	@Autowired
	private BookMapper bookMapper1;
	@Autowired
	private CategoryService categoryService;
	@PostMapping
	public CategoryDto createCategory(@RequestBody CategoryRequest request)
	{
		CategoryDto dto=categoryMappper1.toDto(request);
		categoryService.saveCategory(dto);
		return dto;
	}
	@GetMapping
	public List<CategoryResponse> getAllCategory()
	{
		List<CategoryDto> dtoList=categoryService.getAllCategory();
		List<CategoryResponse> responseList=dtoList.stream().map(c->categoryMappper1.toResponse(c)).collect(Collectors.toList());
		return responseList;
	}
	@GetMapping("/{id}")
	public CategoryDto getCategory(@PathVariable Long id)
	{
		return categoryService.getCategory(id);
	}
	@PutMapping("/{id}")
	public CategoryDto updateBook(@PathVariable Long id, @RequestBody CategoryRequest request)
	{
	    CategoryDto updateBook = categoryMappper1.toDto(request);
	    CategoryDto alreadyUpdatedDto = categoryService.updateCategory(id, updateBook);
	    return alreadyUpdatedDto;
	}
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable Long id)
	{
		categoryService.deleteCategory(id);
	}
	//extra method
//	@GetMapping("/{categoryId}/books")
//	public List<BookResponse> getBooksByCategories(@PathVariable Long categoryId) {
//	    List<BookDto> dtolist = categoryService.getBooksByCategories(categoryId);
//	    List<BookResponse> responseList = dtolist.stream()
//	            .map(b -> {
//	                BookResponse response = bookMapper1.toResponse(b);
//	                response.setCategorydto(b.getCategory());
//	                return response;
//	            })
//	            .collect(Collectors.toList());
//	    return responseList;
//	}

	@PostMapping("/{categoryId}/books")
	public BookDto addBookWithCategory(@PathVariable Long categoryId,@RequestBody BookRequest request)
	{
		BookDto bookDto=bookMapper1.toDto(request);
		BookDto addBookDto=categoryService.addBookWithCategory(categoryId,bookDto);
		return addBookDto;
	}
	@DeleteMapping
	public void deleteAll()
	{
		categoryService.deleteAllCategories();
	}
	

}

