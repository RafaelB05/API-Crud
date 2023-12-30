package com.example.backend.Services;

import com.example.backend.Data.Dto.EmprestimoDTO;
import com.example.backend.Data.Dto.LimitsDTO;
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
            throw new RuntimeException();
        }
        else{
            String url = "localhost:8080/api/pessoa/"+novoEmprestimo.getIdentificadorSolicitante();
            Emprestimo emprestimo = new Emprestimo();
            LimitsDTO response = restTemplate.getForObject(url,LimitsDTO.class);
            double valorParcela = novoEmprestimo.getValorEmprestimo() / novoEmprestimo.getNumeroParcelas();

            if(novoEmprestimo.getValorEmprestimo() > response.getValTotal() || valorParcela < response.getValMin()){
                throw new RuntimeException();
            }
            emprestimo.setValor_emprestimo(novoEmprestimo.getValorEmprestimo());
            emprestimo.setNumero_parcelas(novoEmprestimo.getNumeroParcelas());
            emprestimo.setStatus_pagamento("Aberto");
            emprestimo.setId_pessoa(response.getIdPessoa());
            emprestimo.setData_criacao(novoEmprestimo.getDataCriacao());
            return repository.save(emprestimo);

        }
    }

}
