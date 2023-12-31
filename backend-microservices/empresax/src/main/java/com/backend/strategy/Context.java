package com.backend.strategy;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class Context {
    private Strategy strategy;

    public  double retornaValMin(){
        return strategy.retornaValMin();
    }

    public double retornaValTotal(){
        return strategy.retornaValTotal();
    }

    public boolean validaIdentificador(String identificador){
        return strategy.validaIdentificador(identificador);
    }

    public String retornaTipoIdentificador(){
        return strategy.retornaTipoIdentificador();
    }

}
