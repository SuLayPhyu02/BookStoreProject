package com.nexcode.bookstore.models.requests;

import com.nexcode.bookstore.models.dto.CategoryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

	private String name; 
	private double price;
	private CategoryDto category;
}
