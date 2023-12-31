package com.backend.strategy;

public class ConcreteStrategyEu implements Strategy{
    @Override
    public double retornaValMin(){
        return 100.00;
    }
    @Override
    public double retornaValTotal(){
        return 100000.00;
    }

    @Override
    public boolean validaIdentificador(String identificador) {
        if(identificador.length() == 8){
            int result = Integer.parseInt(String.valueOf(identificador.charAt(0))) + Integer.parseInt(String.valueOf(identificador.charAt(7)));
            return result == 9;
        }
        else
            return false;
    }

    @Override
    public String retornaTipoIdentificador(){
        return "EU";
    }
}
