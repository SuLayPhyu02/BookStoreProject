package com.nexcode.bookstore.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
