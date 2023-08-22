package com.nexcode.bookstore.models.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
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
