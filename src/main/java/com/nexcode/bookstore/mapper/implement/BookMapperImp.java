package com.nexcode.bookstore.mapper.implement;

import org.springframework.stereotype.Component;

import com.nexcode.bookstore.mapper.AuthorMapper;
import com.nexcode.bookstore.mapper.BookMapper;
import com.nexcode.bookstore.mapper.CategoryMapper;
import com.nexcode.bookstore.models.dto.BookDto;
import com.nexcode.bookstore.models.entities.Book;
import com.nexcode.bookstore.models.requests.BookRequest;
import com.nexcode.bookstore.models.response.BookResponse;
@Component
public class BookMapperImp implements BookMapper{
	private final AuthorMapper authormapper;
	private final CategoryMapper categorymapper;
	
	public BookMapperImp(AuthorMapper authormapper, CategoryMapper categorymapper) {
		super();
		this.authormapper = authormapper;
		this.categorymapper = categorymapper;
	}

	@Override
	public BookDto toDto(BookRequest request) {
		BookDto bookdto=new BookDto();
		if(request==null)
		{
			return null;
		}
		bookdto.setName(request.getName());
		bookdto.setPrice(request.getPrice());
		bookdto.setAuthorIds(request.getAuthorIds());
		bookdto.setCategoryId(request.getCategoryId());
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
		bookdto.setCategory(categorymapper.toDto(book.getCategory()));
		bookdto.setAuthordtos(authormapper.toDto(book.getAuthors()));
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
		bookresponse.setCategoryResponse(categorymapper.toResponse(dto.getCategory()));
		bookresponse.setAuthorResponses(authormapper.toResponse(dto.getAuthordtos()));
		return bookresponse;
	}
	

}
