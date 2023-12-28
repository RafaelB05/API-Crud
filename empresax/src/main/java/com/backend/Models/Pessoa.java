package com.backend.Models;

import com.backend.Enum.TipoIdentificador;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Pessoa {
    String nome;
    String identificador;
    TipoIdentificador tipoIdentificador;
    double valParcela;
    double valTotal;
}
