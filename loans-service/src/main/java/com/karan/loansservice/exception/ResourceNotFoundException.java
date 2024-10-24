package com.karan.loansservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resource , String fName , String fVal) {

        super(String.format("%s not found with the given input data %s : '%s'", resource, fName, fVal));
    }
}
