package com.backend.Enum;

import lombok.Getter;
@Getter
public enum TipoIdentificador{
    CPF("CPF"){
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
            return
        }
    },
    CNPJ("CNPJ"){
        @Override
        public double retornaValMin(){
            return 1000.00;
        }
        @Override
        public double retornaValTotal(){
            return 100000.00;
        }

        @Override
        public boolean validaIdentificador(String identificador) {
            return false;
        }
    },
    EU("EU"){
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
    },
    AP("AP"){
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
    };


    public abstract double retornaValMin();

    public abstract double retornaValTotal();

    public abstract boolean validaIdentificador(String identificador);

    private final String tipo;
    TipoIdentificador(String tipo){this.tipo = tipo;}




}
