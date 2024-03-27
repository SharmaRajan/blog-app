package com.codewithrajan.blog.dtoConversion;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codewithrajan.blog.entities.User;
import com.codewithrajan.blog.payloads.UserDTO;

@Component
public class UserDTOConversion {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public User dtoToUser(UserDTO userDTO) {
		/*
		 * User theUser = new User();
		 * 
		 * theUser.setId(userDTO.getId()); theUser.setName(userDTO.getName());
		 * theUser.setPassword(userDTO.getPassword());
		 * theUser.setEmail(userDTO.getEmail()); theUser.setAbout(userDTO.getAbout());
		 */
		
		// using ModelMapper
		User theUser = this.modelMapper.map(userDTO, User.class);
		
		return theUser;
	}
	
	public UserDTO userToDto(User theUser) {
		/*
		 * UserDTO theDto = new UserDTO();
		 * 
		 * theDto.setId(theUser.getId()); theDto.setName(theUser.getName());
		 * theDto.setPassword(theUser.getPassword());
		 * theDto.setEmail(theUser.getEmail()); theDto.setAbout(theUser.getAbout());
		 */
		
		UserDTO theDto = this.modelMapper.map(theUser, UserDTO.class);
		
		return theDto;
	}
	
	
}
