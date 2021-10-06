package com.bridgelabz.fundonotes.Exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bridgelabz.fundonotes.response.ResponseDto;

@ControllerAdvice
public class FunDoNotesException {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseDto> handleCustomException(DataIntegrityViolationException exception){
		ResponseDto responseDTO=new ResponseDto("Email is taken",exception.getMessage());
		return new ResponseEntity<ResponseDto>(responseDTO,HttpStatus.BAD_REQUEST);
	}

}
