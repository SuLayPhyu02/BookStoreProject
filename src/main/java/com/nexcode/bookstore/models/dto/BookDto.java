package com.nexcode.bookstore.models.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
	private Long id;
	private String name;
	private double price;
//	private List<Long>categoryIds;
	private Long categoryId;
	private List<Long>authorIds;
	private CategoryDto category;
	private List<AuthorDto>authordtos;
}
