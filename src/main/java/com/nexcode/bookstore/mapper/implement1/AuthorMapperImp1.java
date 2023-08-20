package com.nexcode.bookstore.mapper.implement1;

import org.springframework.stereotype.Component;

import com.nexcode.bookstore.mapper1.AuthorMapper1;
import com.nexcode.bookstore.models.dto.AuthorDto;
import com.nexcode.bookstore.models.entities.Author;
import com.nexcode.bookstore.models.requests.AuthorRequest;
import com.nexcode.bookstore.models.response.AuthorResponse;
@Component
public class AuthorMapperImp1 implements AuthorMapper1{

	@Override
	public AuthorDto toDto(AuthorRequest request) {
		AuthorDto authorDto=new AuthorDto();
		if(request==null)
		{
			return null;
		}
		authorDto.setName(request.getName());
		return authorDto;
	}

	@Override
	public AuthorDto toDto(Author author) {
		AuthorDto dto=new AuthorDto();
		if(author==null)
		{
			return null;
		}
		dto.setId(author.getAuthorId());
		dto.setName(author.getName());
		return dto;
	}

	@Override
	public AuthorResponse toResponse(AuthorDto dto) {
		AuthorResponse response=new AuthorResponse();
		if(dto==null)
		{
			return null;
		}
		response.setId(dto.getId());
		response.setName(dto.getName());
		return response;
	}

}
