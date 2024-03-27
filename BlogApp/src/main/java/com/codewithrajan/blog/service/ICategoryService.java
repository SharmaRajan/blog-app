package com.codewithrajan.blog.service;

import java.util.List;

import com.codewithrajan.blog.payloads.CategoryDTO;

public interface ICategoryService {

	// create
	CategoryDTO createCategory(CategoryDTO newDto);
	
	// update
	CategoryDTO updateCategory(CategoryDTO newDto, Integer catId);
	
	// delete
	void deleteCategory(Integer catId);
	
	// get
	CategoryDTO getCategoryById(Integer catId);
	
	// getAll
	List<CategoryDTO> getAllCategory();
}
