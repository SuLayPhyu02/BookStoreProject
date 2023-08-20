package com.nexcode.bookstore.mapper1;

import com.nexcode.bookstore.models.dto.AuthorDto;
import com.nexcode.bookstore.models.entities.Author;
import com.nexcode.bookstore.models.requests.AuthorRequest;
import com.nexcode.bookstore.models.response.AuthorResponse;

public interface AuthorMapper1 {

	AuthorDto toDto(AuthorRequest request);
	AuthorDto toDto(Author author);
	AuthorResponse toResponse(AuthorDto dto);
}
