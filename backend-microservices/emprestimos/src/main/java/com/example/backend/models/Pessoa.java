package com.example.backend.models;

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
    private Integer id;
    @Column(name = "identificador",nullable = false,unique = true)
    private String identificador;
    @Column(name="nome",nullable = false)
    private String nome;
    @Column(name="tipo-identificador",nullable = false,length = 4)
    private String tipoIdentificador;
    @Column(name="val-parcela",nullable = false)
    private Double valParcela;
    @Column(name="val-total",nullable = false)
    private Double valTotal;
    @Column(name="data-nasc",nullable = false)
    private Date dataNascimento;
}
