package com.nexcode.bookstore.mapper;

import com.nexcode.bookstore.models.dto.CategoryDto;
import com.nexcode.bookstore.models.entities.Category;
import com.nexcode.bookstore.models.requests.CategoryRequest;
import com.nexcode.bookstore.models.response.CategoryResponse;

public interface CategoryMapper{

	CategoryDto toDto(CategoryRequest request);
	CategoryDto toDto(Category category);
	CategoryResponse toResponse(CategoryDto dto);
//	List<CategoryDto>toDto(List<Category>categories);
//	List<CategoryResponse>toReponse(List<CategoryDto>categorydtos);
}
