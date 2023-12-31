package com.backend.data.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class PessoaDTO implements Serializable {
    private String identificador;
    private String nome;
    private Date dataNascimento;
}
