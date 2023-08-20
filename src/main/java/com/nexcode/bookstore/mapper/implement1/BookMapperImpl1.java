package com.nexcode.bookstore.mapper.implement1;

import org.springframework.stereotype.Component;

import com.nexcode.bookstore.mapper1.BookMapper1;
import com.nexcode.bookstore.models.dto.BookDto;
import com.nexcode.bookstore.models.entities.Book;
import com.nexcode.bookstore.models.requests.BookRequest;
import com.nexcode.bookstore.models.response.BookResponse;
@Component
public class BookMapperImpl1 implements BookMapper1{

	@Override
	public BookDto toDto(BookRequest request) {
		BookDto bookdto=new BookDto();
		if(request==null)
		{
			return null;
		}
		bookdto.setName(request.getName());
		bookdto.setPrice(request.getPrice());
		return bookdto;
	}

	@Override
	public BookDto toDto(Book book) {
		BookDto bookdto=new BookDto();
		if(book==null)
		{
			return null;
		}
		bookdto.setId(book.getBookId());
		bookdto.setName(book.getName());
		bookdto.setPrice(book.getPrice());
		return bookdto;
	}

	@Override
	public BookResponse toResponse(BookDto dto) {
		BookResponse bookresponse=new BookResponse();
		if(dto==null)
		{
			return null;
		}
		bookresponse.setId(dto.getId());
		bookresponse.setName(dto.getName());
		bookresponse.setPrice(dto.getPrice());
		return bookresponse;
	}

}
