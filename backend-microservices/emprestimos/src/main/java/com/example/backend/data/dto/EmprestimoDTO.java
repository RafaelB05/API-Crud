package com.example.backend.data.dto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class EmprestimoDTO {
    private String identificadorSolicitante;
    private double valorEmprestimo;
    private int numeroParcelas;
    private Date dataCriacao;

}
