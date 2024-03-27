package com.codewithrajan.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor//(staticName = "build")
@Getter
@Setter
public class UserDTO {
	
	private int id;
	
	@NotEmpty(message = "name can't be empty")
	@Size(min = 4, message = "username must be min of 4 characters")
	private String name;
	
	@Email(message = "please provide proper email id")
	private String email;
	
	@NotEmpty(message = "Password can't be empty")
	@Size(min = 3, max = 10, message = "Password must be in between 3 to 10 chars !!! ")
//	@Pattern(regexp ="" , = "please provide proper pattern")
	private String password;
	
	@NotEmpty(message = "about can't be empty")
	private String about;

}
