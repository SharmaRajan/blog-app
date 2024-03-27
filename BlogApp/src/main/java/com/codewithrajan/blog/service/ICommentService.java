package com.codewithrajan.blog.service;

import com.codewithrajan.blog.payloads.CommentDTO;

public interface ICommentService {

    CommentDTO createComment(CommentDTO theCommentDTO, Integer postId);

    void deleteComment(Integer commentId);

}
