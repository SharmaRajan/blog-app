package com.codewithrajan.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithrajan.blog.entities.User;

public interface IUserRepo extends JpaRepository<User,Integer> {

    User findByName(String username);

}
