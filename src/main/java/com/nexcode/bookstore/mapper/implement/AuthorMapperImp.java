package com.nexcode.bookstore.mapper.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nexcode.bookstore.mapper.AuthorMapper;
import com.nexcode.bookstore.models.dto.AuthorDto;
import com.nexcode.bookstore.models.entities.Author;
import com.nexcode.bookstore.models.requests.AuthorRequest;
import com.nexcode.bookstore.models.response.AuthorResponse;
@Component
public class AuthorMapperImp implements AuthorMapper{
 
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

	@Override
	public List<AuthorDto> toDto(List<Author> authors) {
		
		
		return authors.stream().map(a->toDto(a)).collect(Collectors.toList());
	}

	@Override
	public List<AuthorResponse> toResponse(List<AuthorDto> authordtos) {
		
		return authordtos.stream().map(a->toResponse(a)).collect(Collectors.toList());
	}

}
