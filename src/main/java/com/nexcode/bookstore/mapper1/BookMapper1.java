package com.nexcode.bookstore.mapper1;

import com.nexcode.bookstore.models.dto.BookDto;
import com.nexcode.bookstore.models.entities.Book;
import com.nexcode.bookstore.models.requests.BookRequest;
import com.nexcode.bookstore.models.response.BookResponse;

public interface BookMapper1 {
	BookDto toDto(BookRequest request);
	BookDto toDto(Book book);
	BookResponse toResponse(BookDto dto);
}
