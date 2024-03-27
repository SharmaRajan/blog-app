package com.codewithrajan.blog.controllers;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithrajan.blog.payloads.ApiResponse;
import com.codewithrajan.blog.payloads.UserDTO;
import com.codewithrajan.blog.service.IUserService;




@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private IUserService userService;
	
	
	// POST - create User
	@PostMapping("/lists")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO theDto){
		UserDTO createdUserDTO = userService.createUser(theDto);
		return new ResponseEntity<>(createdUserDTO,HttpStatus.CREATED);
	}
	
	// PUT - update User
	@PutMapping("/lists/{id}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO theDto, @PathVariable Integer id){
		UserDTO updatedUser = this.userService.updateUser(theDto, id);
		return ResponseEntity.ok(updatedUser);
	}
	
	// DELETE - delete User
	@DeleteMapping("/lists/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
//		return new ResponseEntity<>(Map.of("message","User Deleted successfully"),HttpStatus.OK);
		return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	
	// GET - get User
	@GetMapping("/lists")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> usrListsDtos = this.userService.getAllUsers();
		return new ResponseEntity<>(usrListsDtos,HttpStatus.OK);
	}
	
	@GetMapping("/lists/{userId}")
	public ResponseEntity<UserDTO> getSingleUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
}
