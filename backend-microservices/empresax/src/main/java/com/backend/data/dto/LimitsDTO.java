package com.backend.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LimitsDTO {
    private Integer idPessoa;
    private Double valTotal;
    private Double valMin;
}
