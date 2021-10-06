package com.bridgelabz.fundonotes.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundonotes.Exception.FunDoNotesCutomException;
import com.bridgelabz.fundonotes.config.PasswordEncoderConfig;
import com.bridgelabz.fundonotes.dto.LoginDto;
import com.bridgelabz.fundonotes.dto.UserDto;
import com.bridgelabz.fundonotes.entity.User;
import com.bridgelabz.fundonotes.repo.RepositoryLayer;
import com.bridgelabz.fundonotes.utils.EmailSender;
import com.bridgelabz.fundonotes.utils.TokenService;

@Service
public class IServiceLayer implements ServiceLayer {
	
	@Autowired
	RepositoryLayer repositoryLayer;
	
	@Autowired
	PasswordEncoderConfig pec;
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
	TokenService tokenService;
	
	public User register(UserDto userDto) {
		User userEntity = new User();
		BeanUtils.copyProperties(userDto, userEntity);
		userEntity.setPassword(pec.pass().encode(userDto.getPassword()));
		User user = repositoryLayer.save(userEntity);
		
		String token = tokenService.createToken(user.getId());
		sendEmail(userDto,token);
		return user;
	}
	
	public String login(LoginDto loginDto) {
		User user = repositoryLayer.findByEmail(loginDto.getEmail());
		if (user == null) {
			throw new FunDoNotesCutomException(HttpStatus.BAD_REQUEST,"Email-Id incorrect");
		}
		if (!(pec.pass().matches(loginDto.getPassword(), user.getPassword()))) {
			throw new FunDoNotesCutomException(HttpStatus.BAD_REQUEST,"Password Incorrect");
		}
		return "token";
		
	}
	
	public void sendEmail(UserDto userDto, String token) {
		String email = userDto.getEmail();
		String from = "Nikhilmusale70@gmail.com";
		String subject = "Verification email";
		String body = "http://localhost:8080/verify/" + token;
		Boolean isSent = emailSender.isEmailSent(email, from, subject, body);
		if (!isSent) {
			throw new FunDoNotesCutomException(HttpStatus.BAD_GATEWAY,"Email not sent");
		}
	}
	
	public void verifyEmail(String token) {
		int userId = tokenService.decodeToken(token);
		User user = repositoryLayer.findById(userId).orElseThrow
				( () -> new FunDoNotesCutomException(HttpStatus.BAD_REQUEST,"User Not Present"));
		user.setEmailVerified(true);
		repositoryLayer.save(user);
	}
	
	
}
