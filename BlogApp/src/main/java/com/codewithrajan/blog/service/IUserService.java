package com.codewithrajan.blog.service;

import java.util.List;

import com.codewithrajan.blog.payloads.UserDTO;

public interface IUserService {

	UserDTO createUser(UserDTO theUser);
	
	UserDTO updateUser(UserDTO theUser, Integer userId);
	
	UserDTO getUserById(Integer userId);
	
	List<UserDTO> getAllUsers();
	
	void deleteUser(Integer userId);
	
}
