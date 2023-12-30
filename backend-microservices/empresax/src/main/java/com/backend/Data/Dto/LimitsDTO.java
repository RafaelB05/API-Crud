package com.backend.Data.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LimitsDTO {
    private int idPessoa;
    private double valTotal;
    private double valMin;
}
