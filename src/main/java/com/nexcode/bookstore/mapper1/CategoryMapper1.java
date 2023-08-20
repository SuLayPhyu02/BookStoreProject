package com.nexcode.bookstore.mapper1;

import com.nexcode.bookstore.models.dto.CategoryDto;
import com.nexcode.bookstore.models.entities.Category;
import com.nexcode.bookstore.models.requests.CategoryRequest;
import com.nexcode.bookstore.models.response.CategoryResponse;

public interface CategoryMapper1{

	CategoryDto toDto(CategoryRequest request);
	CategoryDto toDto(Category category);
	CategoryResponse toResponse(CategoryDto dto);
}
