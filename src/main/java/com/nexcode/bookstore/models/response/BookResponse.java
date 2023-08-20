package com.nexcode.bookstore.models.response;

import com.nexcode.bookstore.models.dto.CategoryDto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
	
	private Long id;
	private String name;
	private double price;
	private CategoryDto category;
}
