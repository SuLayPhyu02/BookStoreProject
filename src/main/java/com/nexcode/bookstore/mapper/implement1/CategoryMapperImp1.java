package com.nexcode.bookstore.mapper.implement1;

import org.springframework.stereotype.Component;

import com.nexcode.bookstore.mapper1.CategoryMapper1;
import com.nexcode.bookstore.models.dto.CategoryDto;
import com.nexcode.bookstore.models.entities.Category;
import com.nexcode.bookstore.models.requests.CategoryRequest;
import com.nexcode.bookstore.models.response.CategoryResponse;
@Component
public class CategoryMapperImp1 implements CategoryMapper1{

	@Override
	public CategoryDto toDto(CategoryRequest request) {
		CategoryDto dto=new CategoryDto();
		if(request==null)
		{
			return null;
		}
		dto.setName(request.getName());
		return dto;
	}

	@Override
	public CategoryDto toDto(Category category) {
		CategoryDto dto=new CategoryDto();
		if(category==null)
		{
			return null;
		}
		dto.setId(category.getCategoryId());
		dto.setName(category.getName());
		return dto;
	}

	@Override
	public CategoryResponse toResponse(CategoryDto dto) {
		CategoryResponse response=new CategoryResponse();
		if(dto==null)
		{
			return null;
		}
		response.setId(dto.getId());
		response.setName(dto.getName());
		return response;
	}

}
