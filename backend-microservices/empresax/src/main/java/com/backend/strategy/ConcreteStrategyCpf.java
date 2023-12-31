package com.backend.strategy;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ConcreteStrategyCpf implements Strategy {

    @Override
    public double retornaValMin(){
        return 300.00;
    }
    @Override
    public double retornaValTotal(){
        return 10000.00;
    }

    @Override
    public boolean validaIdentificador(String identificador) {
        int digito1, digito2,digitoCPF;
        int d1 = 0, d2 = 0 ,resto;
        for(int i = 1; i < identificador.length()-1;i++){
            digitoCPF = Integer.parseInt(identificador.substring(i - 1, i));
            d1 = d1 +(11-i) * digitoCPF;
            d2 = d2 +(12-i) * digitoCPF;

        }
        resto = (d1 % 11);
        if(resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d2 += 2 * digito1;

        resto = (d2%11);

        if(resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        String nDigVerific = identificador.substring(identificador.length() - 2);
        String nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        return nDigVerific.equals(nDigResult);
    }

    @Override
    public String retornaTipoIdentificador(){
        return "CPF";
    }
}
