package com.backend.strategy;

public class ConcreteStrategyAp implements Strategy{
    @Override
    public double retornaValMin(){
        return 400.00;
    }
    @Override
    public double retornaValTotal(){
        return 25000.00;
    }

    @Override
    public boolean validaIdentificador(String identificador) {
        if(identificador.length() == 10){
            String verify = identificador.substring(0,6);
            return verify.contains(identificador.subSequence(7,7));
        }
        else
            return false;
    }

    @Override
    public String retornaTipoIdentificador(){
        return "AP";
    }
}
