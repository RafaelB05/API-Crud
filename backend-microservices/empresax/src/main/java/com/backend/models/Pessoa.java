package com.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "identificador",nullable = false,unique = true)
    private String identificador;
    @Column(name="nome",nullable = false)
    private String nome;
    @Column(name="tipo-identificador",nullable = false,length = 4)
    private String tipoIdentificador;
    @Column(name="val-parcela",nullable = false)
    private double valParcela;
    @Column(name="val-total",nullable = false)
    private double valTotal;
    @Column(name="data-nasc",nullable = false)
    private Date dataNascimento;
}
