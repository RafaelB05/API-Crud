package com.backend.exceptions;

import java.io.Serializable;

public class DuplicatedEntityException extends RuntimeException implements Serializable {
    public DuplicatedEntityException(String ex){
        super(ex);
    }
}
