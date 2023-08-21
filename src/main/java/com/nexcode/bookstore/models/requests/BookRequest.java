package com.nexcode.bookstore.models.requests;

import java.util.List;

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
	private List<Long>author_id;
	private Long categoryId;
}
