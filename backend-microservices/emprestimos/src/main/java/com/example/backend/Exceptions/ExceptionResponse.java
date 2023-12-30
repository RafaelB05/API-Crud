package com.example.backend.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@Getter
public class ExceptionResponse implements Serializable {
   private Date timestamp;
   private  String message;
   private String detalhes;
}
