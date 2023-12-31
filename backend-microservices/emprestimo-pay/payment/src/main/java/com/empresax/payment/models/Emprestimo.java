package com.empresax.payment.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(name = "id_pessoa")
    private int idPessoa;
    @Column(name = "valor_emprestimo",nullable = false)
    private double valorEmprestimo;
    @Column(name = "numero_parcelas",nullable = false)
    private int numeroParcelas;
    @Column(name = "status_pagamento",nullable = false)
    private String statusPagamento;
    @Column(name = "data_criacao",nullable = false)
    private Date dataCriacao;


}
