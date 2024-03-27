package com.codewithrajan.blog.service;

import com.codewithrajan.blog.payloads.PostDTO;
import com.codewithrajan.blog.payloads.PostResponse;

import java.util.List;

public interface IPostService {

	// create
	PostDTO createPost(PostDTO newPost, Integer userId, Integer categoryId);

    // update
    PostDTO updatePost(PostDTO newPost, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get
    PostDTO getPostById(Integer postId);

    // get all
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection);

    // get all post by category
    List<PostDTO> getPostByCategory(Integer categoryId);

    // get all posts by User
    List<PostDTO> getPostsByUser(Integer userId);

    // search posts
    List<PostDTO> searchPost(String keyword);

}
