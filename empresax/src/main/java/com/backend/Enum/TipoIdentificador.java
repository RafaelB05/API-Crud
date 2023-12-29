package com.backend.Enum;

import lombok.Getter;

@Getter
public enum TipoIdentificador{
    CPF("CPF"){
        public double retornaValMin(){
            return 300.00;
        }

        public double retornaValTotal(){
            return 10000.00;
        }
    },
    CNPJ("CNPJ"){
        public double retornaValMin(){
            return 1000.00;
        }
        public double retornaValTotal(){
            return 100000.00;
        }
    },
    EU("EU"){
        public double retornaValMin(){
            return 100.00;
        }

        public double retornaValTotal(){
            return 100000.00;
        }
    },
    AP("AP"){
        public double retornaValMin(){
            return 400.00;
        }

        public double retornaValTotal(){
            return 25000.00;
        }
    };


    public abstract double retornaValMin();

    public abstract double retornaValTotal();

    private final String tipo;
    TipoIdentificador(String tipo){this.tipo = tipo;}




}
