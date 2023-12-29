package com.backend.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
