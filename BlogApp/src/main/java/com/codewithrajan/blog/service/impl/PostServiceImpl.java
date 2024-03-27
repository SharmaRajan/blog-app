package com.codewithrajan.blog.service.impl;

import com.codewithrajan.blog.entities.Category;
import com.codewithrajan.blog.entities.Post;
import com.codewithrajan.blog.entities.User;
import com.codewithrajan.blog.exceptions.ResourceNotFoundException;
import com.codewithrajan.blog.payloads.PostDTO;
import com.codewithrajan.blog.payloads.PostResponse;
import com.codewithrajan.blog.repository.ICategoryRepo;
import com.codewithrajan.blog.repository.IPostRepo;
import com.codewithrajan.blog.repository.IUserRepo;
import com.codewithrajan.blog.service.IPostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepo postRepo;

    // constructor injection
//    @Autowired
//    public PostServiceImpl(PostRepo theRepo){
//        this.postRepo = theRepo;
//    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private ICategoryRepo categoryRepo;

    @Override
    public PostDTO createPost(PostDTO newPost, Integer userId, Integer categoryId) {

            User theUser = this.userRepo.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User ","user id: " ,userId));

            Category theCategory = this.categoryRepo.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "category id: ", categoryId));

            Post post = this.modelMapper.map(newPost, Post.class);
//            post.setImageName("default.png");
            post.setAddedDate(new Date());
            post.setUser(theUser);
            post.setCategory(theCategory);

            Post thePost = this.postRepo.save(post);

            return this.modelMapper.map(thePost,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO newPost, Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id: ", postId));

        post.setTitle(newPost.getTitle());
        post.setContent(newPost.getContent());
        post.setImageName(newPost.getImageName());

        Post updatedPost = this.postRepo.save(post);

        PostDTO updatedPostDto = this.modelMapper.map(updatedPost,PostDTO.class);

        return updatedPostDto;
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id: ", postId));

        this.postRepo.delete(post);
    }

    @Override
    public PostDTO getPostById(Integer postId) {

        Post thePost = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id:", postId));
        PostDTO postDTO = this.modelMapper.map(thePost,PostDTO.class);
        return postDTO;
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        /*if(sortDirection.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }else if(sortDirection.equalsIgnoreCase("desc")){
            sort  = Sort.by(sortBy).descending();
        }*/

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Post> postPage = this.postRepo.findAll(pageable);
        List<Post> postList = postPage.getContent();
//        List<Post> postList = postPage.stream().toList();

//        List<Post> postList = this.postRepo.findAll();

        List<PostDTO> postDTOList = postList.stream().map(post -> this.modelMapper.map(post,PostDTO.class))
                                                                                                .collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDTOList);
        postResponse.setPageNumber(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());

        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;
    }

    @Override
    public List<PostDTO> getPostByCategory(Integer categoryId) {
        Category theCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id: ",categoryId));
        List<Post> postList = this.postRepo.findByCategory(theCategory);

        List<PostDTO>  postDTOS = postList.stream().map(post -> this.modelMapper.map(post,PostDTO.class))
                                                                        .collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {

        User theUser = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", " user id: ", userId));

        List<Post> postList = this.postRepo.findByUser(theUser);

        List<PostDTO> postDTOS = postList.stream().map(post -> modelMapper.map(post,PostDTO.class))
                                                                            .collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> searchPost(String keyword) {

        // 1st method to search
        List<Post> postList =  this.postRepo.findByTitleContaining(keyword);

        // 2nd method to search
//        List<Post> postList = this.postRepo.searchByTitle("%" + keyword + "%");

        List<PostDTO> postDTOList = postList.stream()
                .map(post -> this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());

        return postDTOList;
    }
}
