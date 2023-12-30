package com.example.backend.Models;

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
public class Emprestimo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(name = "id_pessoa")
    private int id_pessoa;
    @Column(name = "valor_emprestimo",nullable = false)
    private double valor_emprestimo;
    @Column(name = "numero_parcelas",nullable = false)
    private int numero_parcelas;
    @Column(name = "status_pagamento",nullable = false)
    private String status_pagamento;
    @Column(name = "data_criacao",nullable = false)
    private Date data_criacao;


}
