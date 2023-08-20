package com.nexcode.bookstore.models.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity(name="authors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="author_id")
	private Long authorId;
	@Column(name="author_name")
	private String name;
	@ManyToMany(mappedBy = "authors")
	private List<Book> books;
}
