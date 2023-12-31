package com.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EntityNotFoundException extends RuntimeException implements Serializable {
    public EntityNotFoundException(String ex){
        super(ex);
    }
}
