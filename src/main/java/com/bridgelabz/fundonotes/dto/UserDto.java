package com.bridgelabz.fundonotes.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@NotNull(message = "First name should not be blank")
	private String fname;
	@NotNull(message = "Last name should not be blank")
	private String lname;
	@NotNull(message = "Phone Number should not be blank")
	private String phoneNumber;	
	@NotNull(message = "Email should not be blank")
	private String email;
	@NotNull(message = "Password should not be blank")
	private String password;
}
