package com.mergeproject.customexception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;




@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(CustomeException.class)
	public ResponseEntity<com.mergeproject.entity.Result>response(CustomeException ex)
	{
		
	//	Map<String,Object>response=new HashMap<>();
		
		com.mergeproject.entity.Result result=new com.mergeproject.entity.Result();
		result.setResponse(ex.getMessage());
		
		
		result.setStatus(String.valueOf(HttpStatus.BAD_REQUEST));

		return ResponseEntity.ok(result);
		
	}
	@ExceptionHandler(UserIdNotFound.class)
	public ResponseEntity<com.mergeproject.entity.ResponseStatus> result(UserIdNotFound pif) {

		com.mergeproject.entity.ResponseStatus status = new com.mergeproject.entity.ResponseStatus();

		status.setResponse(pif.getMessage());
		status.setStatus(String.valueOf(HttpStatus.NOT_FOUND));

		return ResponseEntity.ok(status);
	}

}
