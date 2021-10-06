package com.bridgelabz.fundonotes.Exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FunDoNotesCutomException extends RuntimeException {
	
	private HttpStatus httpStatus;
	private String message;
	
}
