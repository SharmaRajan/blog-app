package com.codewithrajan.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithrajan.blog.payloads.ApiResponse;
import com.codewithrajan.blog.payloads.CategoryDTO;
import com.codewithrajan.blog.service.ICategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	
	@Autowired
	private ICategoryService categoryService;
	
	// create
	@PostMapping("/lists")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO theCategoryDTO){
		
		CategoryDTO theDto = this.categoryService.createCategory(theCategoryDTO);
		return new ResponseEntity<>(theDto,HttpStatus.CREATED);
	}
	
	// update
	@PutMapping("/lists/{catId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO theCategoryDTO, @PathVariable Integer catId){
		
		CategoryDTO updatedCategory = this.categoryService.updateCategory(theCategoryDTO,catId);
		return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
	}
	
	// delete
	@DeleteMapping("/lists/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		this.categoryService.deleteCategory(catId); 
		return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true),HttpStatus.OK);
	}
	
	// get
	@GetMapping("/lists/{catId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer catId){
		CategoryDTO theDto = categoryService.getCategoryById(catId);
		return new ResponseEntity<CategoryDTO>(theDto,HttpStatus.OK);
	}
	
	// getAll
	@GetMapping("/lists")
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		List<CategoryDTO> categories = categoryService.getAllCategory();
//		return new ResponseEntity<List<CategoryDTO>>(theDto,HttpStatus.OK);
		return ResponseEntity.ok(categories);
	}
	
}









