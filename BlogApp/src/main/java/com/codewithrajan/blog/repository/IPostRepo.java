package com.codewithrajan.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithrajan.blog.entities.Category;
import com.codewithrajan.blog.entities.Post;
import com.codewithrajan.blog.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IPostRepo extends JpaRepository<Post, Integer> {

//	List<Post> getAllByUser(User theUser);
//	List<Post> findByUser(User user);
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category theCategory);

	List<Post> findByTitleContaining(String title);

	@Query(value = "select p from Post p where p.title like :key ")
	List<Post> searchByTitle(@Param("key") String title);
	
}
