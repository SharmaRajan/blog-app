package com.codewithrajan.blog.controllers;

import com.codewithrajan.blog.payloads.ApiResponse;
import com.codewithrajan.blog.payloads.CommentDTO;
import com.codewithrajan.blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    // save comment
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO theDto, @PathVariable Integer postId){

        CommentDTO commentDTO = this.commentService.createComment(theDto,postId);
        return new ResponseEntity<>(commentDTO, HttpStatus.CREATED);
    }

    // delete comment by comment Id
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer commentId){

        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("comment deleted successfully !!!", true),HttpStatus.OK);
    }

}
