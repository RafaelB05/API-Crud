package com.example.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class IllegalValueException extends  RuntimeException implements Serializable {
    public IllegalValueException(String ex){
        super(ex);
    }
}
