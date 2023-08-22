package com.nexcode.bookstore.models.requests;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {

	private String name; 
	private double price;
	private List<Long>authorIds;
	private Long categoryId;
}
