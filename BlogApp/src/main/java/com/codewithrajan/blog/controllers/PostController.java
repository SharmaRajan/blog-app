package com.codewithrajan.blog.controllers;

import com.codewithrajan.blog.helper.AppConstants;
import com.codewithrajan.blog.payloads.ApiResponse;
import com.codewithrajan.blog.payloads.PostDTO;
import com.codewithrajan.blog.payloads.PostResponse;
import com.codewithrajan.blog.service.IFileService;
import com.codewithrajan.blog.service.IPostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private IPostService postService;

    @Autowired
    private IFileService fileService;

    @Value("${project.image}")
    private String path;

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO thePostDto,
                                              @PathVariable Integer userId, @PathVariable Integer categoryId){

        PostDTO createdPost = this.postService.createPost(thePostDto,userId,categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // get all posts by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<?> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDTO> postDTOList =  this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }


    // get all posts by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<?> getPostsByUser(@PathVariable Integer userId){
        List<PostDTO> postDTOList =  this.postService.getPostsByUser(userId);
        return new ResponseEntity<>(postDTOList,HttpStatus.OK);
    }

    // get all posts
    @GetMapping("/posts")
    public ResponseEntity<?> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = AppConstants.SORT_DIRECTION,required = false) String sortDirection
            ){
        PostResponse postRes = this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDirection);
        return new ResponseEntity<>(postRes,HttpStatus.OK);
    }

    // get post details by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getAllPosts(@PathVariable Integer postId){
        PostDTO postDTO = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDTO,HttpStatus.OK);
    }

    // update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<?> updatePostById(@RequestBody PostDTO postDTO ,@PathVariable Integer postId){
        PostDTO updatedPost = this.postService.updatePost(postDTO,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    // delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePostById(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted",true);
    }

    // search by title
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<?> searchPosts(@PathVariable String keywords){
        List<PostDTO> resultList = this.postService.searchPost(keywords);
        return new ResponseEntity<>(resultList,HttpStatus.OK);
    }

    // post image upload
    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDTO> uploadPostImage(
            @RequestParam("image")MultipartFile file,
            @PathVariable Integer postId
            ) throws IOException {

        PostDTO postDTO = this.postService.getPostById(postId);

        String fileName = this.fileService.uploadImage(path,file);

        postDTO.setImageName(fileName);
        PostDTO updatedPost = this.postService.updatePost(postDTO,postId);

        return new ResponseEntity<PostDTO>(updatedPost,HttpStatus.OK);
    }

    // to download image
    @GetMapping(value = "/posts/image/download/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable String imageName,
            HttpServletResponse response
    ) throws FileNotFoundException, IOException {
        InputStream resource = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
