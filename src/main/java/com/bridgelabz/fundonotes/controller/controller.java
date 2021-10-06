package com.bridgelabz.fundonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundonotes.dto.LoginDto;
import com.bridgelabz.fundonotes.dto.UserDto;
import com.bridgelabz.fundonotes.entity.User;
import com.bridgelabz.fundonotes.response.ResponseDto;
import com.bridgelabz.fundonotes.service.IServiceLayer;

@RestController
public class controller {
	
	@Autowired
	IServiceLayer serviceLayer;	
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDto> post(@RequestBody UserDto userDto ) {
		User user = serviceLayer.register(userDto);
		ResponseDto responseDto = new ResponseDto("Added succesfully",user);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@RequestBody LoginDto loginDto) {
		String token = serviceLayer.login(loginDto);
		ResponseDto responseDto = new ResponseDto("LoggedIn succesfully",token);
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
	
	@GetMapping("/verify/{token}")
	public ResponseEntity<ResponseDto> verifyEmail(@PathVariable(value = "token") String token) {
		serviceLayer.verifyEmail(token);
		ResponseDto responseDto = new ResponseDto("User email verified","");
		return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
	}
}
