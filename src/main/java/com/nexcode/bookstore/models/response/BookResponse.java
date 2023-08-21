package com.nexcode.bookstore.models.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
	
	private Long id;
	private String name;
	private double price;
	private CategoryResponse categoryResponse;
	private List<AuthorResponse>authorResponses;
}
