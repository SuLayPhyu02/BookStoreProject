package com.nexcode.bookstore.mapper;

import java.util.List;

import com.nexcode.bookstore.models.dto.AuthorDto;
import com.nexcode.bookstore.models.entities.Author;
import com.nexcode.bookstore.models.requests.AuthorRequest;
import com.nexcode.bookstore.models.response.AuthorResponse;

public interface AuthorMapper {

	AuthorDto toDto(AuthorRequest request);
	AuthorDto toDto(Author author);
	AuthorResponse toResponse(AuthorDto dto);
	List<AuthorDto>toDto(List<Author>authors);
	List<AuthorResponse>toResponse(List<AuthorDto>authordtos);
}
