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
            if(!TipoIdentificador.cpnjInvalido(identificador)){
                char digito13,digito14;
                int sm, r, num, peso;
                sm = 0;
                peso = 2;
                for(int i = 11; i >= 0; i--){
                    num = identificador.charAt(i) - 48;
                    sm += (num * peso);
                    peso += 1;
                    if(peso == 10)
                        peso = 2;
                }
                r = sm % 11;
                if(r == 0 || r == 1)
                    digito13 = '0';
                else
                    digito13 = (char) ((11-r)+48);

                sm = 0;
                peso = 2;

                for(int i = 12; i >= 0; i--){
                    num = identificador.charAt(i) - 48;
                    sm += (num * peso);
                    peso += 1;
                    if(peso == 10)
                        peso = 2;
                }
                r = sm % 11;
                if(r == 0 || r == 1)
                    digito14 = '0';
                else
                    digito14 = (char) ((11-r)+48);

                return (digito13 == identificador.charAt(12) && digito14 == identificador.charAt(13));
            }
            else{
                return false;
            }
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

    private static boolean cpnjInvalido(String cnpj) {
        return "00000000000000".equals(cnpj) ||
                "11111111111111".equals(cnpj) ||
                "22222222222222".equals(cnpj) ||
                "33333333333333".equals(cnpj) ||
                "44444444444444".equals(cnpj) ||
                "55555555555555".equals(cnpj) ||
                "66666666666666".equals(cnpj) ||
                "77777777777777".equals(cnpj) ||
                "88888888888888".equals(cnpj) ||
                "99999999999999".equals(cnpj);
    }



}