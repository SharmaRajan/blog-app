package com.codewithrajan.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithrajan.blog.entities.Category;

public interface ICategoryRepo extends JpaRepository<Category, Integer>{

}
