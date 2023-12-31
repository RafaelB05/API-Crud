package com.backend.strategy;

public class ConcreteStrategyCnpj implements Strategy{
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
        if(!cpnjInvalido(identificador)){
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
    @Override
    public String retornaTipoIdentificador(){
        return "CNPJ";
    }

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
