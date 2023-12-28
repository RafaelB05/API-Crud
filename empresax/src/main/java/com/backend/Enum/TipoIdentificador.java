package com.backend.Enum;

import lombok.Getter;

@Getter
public enum TipoIdentificador{
    CPF(11){
        public double retornaValMin(){
            return 300.00;
        }

        public double retornaValTotal(){
            return 10000.00;
        }
    },
    CNPJ(14){
        public double retornaValMin(){
            return 1000.00;
        }
        public double retornaValTotal(){
            return 100000.00;
        }
    },
    EU(8){
        public double retornaValMin(){
            return 100.00;
        }

        public double retornaValTotal(){
            return 100000.00;
        }
    },
    AP(10){
        public double retornaValMin(){
            return 400.00;
        }

        public double retornaValTotal(){
            return 25000.00;
        }
    };


    public abstract double retornaValMin();

    public abstract double retornaValTotal();

    private final int size;
    TipoIdentificador(int tamanho){
        size = tamanho;
    }




}
