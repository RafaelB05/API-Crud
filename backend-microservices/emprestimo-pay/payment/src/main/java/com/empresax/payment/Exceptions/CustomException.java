package com.empresax.payment.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomException extends  RuntimeException implements Serializable {
    public CustomException(String ex){
        super(ex);
    }
}