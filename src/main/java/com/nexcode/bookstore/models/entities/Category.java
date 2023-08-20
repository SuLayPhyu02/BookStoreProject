package com.nexcode.bookstore.models.entities;

import javax.persistence.*;

import lombok.*;
import java.util.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")
public class Category {
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Long categoryId;
	@Column(name="category_name")
	private String name;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="fk_category_id", referencedColumnName = "category_id")
	private List<Book> books;
}
