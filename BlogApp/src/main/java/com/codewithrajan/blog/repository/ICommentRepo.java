package com.codewithrajan.blog.repository;

import com.codewithrajan.blog.entities.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<Comment, Integer> {
}
