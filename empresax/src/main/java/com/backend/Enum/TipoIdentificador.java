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
            int digito1, digito2,digitoCPF;
            int d1 = 0, d2 = 0 ,resto = 0;
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
           return  false;
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
