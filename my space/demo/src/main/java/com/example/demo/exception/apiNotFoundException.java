


package com.example.demo.exception;
public class apiNotFoundException extends RuntimeException {
	public apiNotFoundException(Long id){
        super("Could not found the user with id "+ id);
    }

}
