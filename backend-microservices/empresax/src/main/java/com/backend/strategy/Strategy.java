package com.backend.strategy;

public interface Strategy {

    double retornaValMin();

    double retornaValTotal();

    String retornaTipoIdentificador();

    boolean validaIdentificador(String identificador);

}
