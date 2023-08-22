package com.nexcode.bookstore.models.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookResponse {
	
	private Long id;
	private String name;
	private double price;
	private CategoryResponse categoryResponse;
	private List<AuthorResponse>authorResponses;
}
