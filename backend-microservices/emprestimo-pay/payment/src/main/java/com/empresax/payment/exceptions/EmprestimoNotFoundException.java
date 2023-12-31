package com.empresax.payment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmprestimoNotFoundException extends  RuntimeException implements Serializable {
    public EmprestimoNotFoundException(String ex){
        super(ex);
    }
}