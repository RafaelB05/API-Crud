package com.example.backend.Services;

import com.example.backend.Data.Dto.EmprestimoDTO;
import com.example.backend.Data.Dto.LimitsDTO;
import com.example.backend.Exceptions.CustomException;
import com.example.backend.Models.Emprestimo;
import com.example.backend.Repositories.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public Emprestimo realizarEmprestimo(EmprestimoDTO novoEmprestimo){

        if(novoEmprestimo.getNumeroParcelas() > 24){
            throw new CustomException("A quantidade de parcelas excede o permitido");
        }
        else{
            String url = "http://localhost:8080/api/pessoa/internal/"+novoEmprestimo.getIdentificadorSolicitante();
            Emprestimo emprestimo = new Emprestimo();

            LimitsDTO response = restTemplate.getForObject(url,LimitsDTO.class);

            if(validaValorParcela(novoEmprestimo.getValorEmprestimo(),novoEmprestimo.getNumeroParcelas(),response.getValMin()) && validaValorTotal(novoEmprestimo.getValorEmprestimo(),response.getValTotal())){
                emprestimo.setValor_emprestimo(novoEmprestimo.getValorEmprestimo());
                emprestimo.setNumero_parcelas(novoEmprestimo.getNumeroParcelas());
                emprestimo.setStatus_pagamento("Aberto");
                emprestimo.setId_pessoa(response.getIdPessoa());
                emprestimo.setData_criacao(novoEmprestimo.getDataCriacao());
                emprestimo = repository.save(emprestimo);

                url = "http://localhost:8081/api/emprestimo/payment/"+emprestimo.getId();
                return restTemplate.postForObject(url,emprestimo,Emprestimo.class);
            }
            else{
                throw  new CustomException("Valores incompativeis");
            }


        }
    }

    private boolean validaValorTotal(double valorEstimado, double valorPermitido){
        return valorEstimado < valorPermitido;
    }

    private boolean validaValorParcela(double valorEstimado, int numeroParcelas, double valorPermitido){
        double valorParcela = valorEstimado / numeroParcelas;
        return  valorParcela > valorPermitido;
    }

}


