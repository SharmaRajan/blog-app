package com.codewithrajan.blog.service.impl;

import com.codewithrajan.blog.entities.Comment;
import com.codewithrajan.blog.entities.Post;
import com.codewithrajan.blog.exceptions.ResourceNotFoundException;
import com.codewithrajan.blog.payloads.CommentDTO;
import com.codewithrajan.blog.repository.ICommentRepo;
import com.codewithrajan.blog.repository.IPostRepo;
import com.codewithrajan.blog.service.ICommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private IPostRepo postRepo;

    @Autowired
    private ICommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO theCommentDTO, Integer postId) {

        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID: ",postId));

        Comment comment = this.modelMapper.map(theCommentDTO, Comment.class);

        comment.setPost(post);

        Comment savedComment = this.commentRepo.save(comment);

        CommentDTO savedCommentDto = this.modelMapper.map(savedComment,CommentDTO.class);
        return savedCommentDto;
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id: ",commentId));

        this.commentRepo.delete(comment);
    }
}
