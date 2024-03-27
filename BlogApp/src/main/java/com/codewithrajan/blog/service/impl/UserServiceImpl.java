package com.codewithrajan.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.codewithrajan.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithrajan.blog.dtoConversion.UserDTOConversion;
import com.codewithrajan.blog.entities.User;
import com.codewithrajan.blog.exceptions.ResourceNotFoundException;
import com.codewithrajan.blog.payloads.UserDTO;
import com.codewithrajan.blog.repository.IUserRepo;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private UserDTOConversion conversion;
	
	@Override
	public UserDTO createUser(UserDTO theUserDto) {
		
		// another alternative is ModelMapper
		//UserDTOConversion conversion = new UserDTOConversion();
		
		// convert the dto to entity
		User theUser = conversion.dtoToUser(theUserDto);
		
		// save the user
		User savedUser = this.userRepo.save(theUser);
		
		// return the conversion dto of saved user entity
		return conversion.userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO theUserDto, Integer userId) {
		
		User theUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","Id ",userId));
		
		theUser.setName(theUserDto.getName());
		theUser.setEmail(theUserDto.getEmail());
		theUser.setPassword(theUserDto.getPassword());
		theUser.setAbout(theUserDto.getAbout());
		
		User savedUser = this.userRepo.save(theUser);
		
		// convert entity to dto to transfer the response
		//UserDTOConversion conversion = new UserDTOConversion();
		UserDTO theDto = conversion.userToDto(savedUser);
		
		return theDto;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		
		User theUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","Id ",userId));
		
		// convert entity to dto to transfer the response
		//UserDTOConversion conversion = new UserDTOConversion();
		
		// directly use model mapper now
		UserDTO userDTO = conversion.userToDto(theUser);
		
		return userDTO;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		
		//UserDTOConversion conversion = new UserDTOConversion();
		
		List<UserDTO> userDtoList =  users.stream()
				.map(user -> conversion.userToDto(user)).collect(Collectors.toList());
		
		return userDtoList;
		
	}

	@Override
	public void deleteUser(Integer userId) {

		User theUser = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","Id ",userId));
		
		userRepo.delete(theUser);
		
	}

}
