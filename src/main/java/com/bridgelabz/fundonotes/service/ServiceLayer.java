package com.bridgelabz.fundonotes.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundonotes.dto.LoginDto;
import com.bridgelabz.fundonotes.dto.UserDto;
import com.bridgelabz.fundonotes.entity.User;

@Service
public interface ServiceLayer {
	
	public User register(UserDto userDto);
	public String login(LoginDto loginDto);
	public void verifyEmail(String token);
}
