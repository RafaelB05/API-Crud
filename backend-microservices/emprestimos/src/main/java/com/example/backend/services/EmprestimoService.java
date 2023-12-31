package com.example.backend.services;

import com.example.backend.data.dto.EmprestimoDTO;
import com.example.backend.data.dto.LimitsDTO;
import com.example.backend.exceptions.IllegalValueException;
import com.example.backend.models.Emprestimo;
import com.example.backend.repositories.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public Emprestimo realizarEmprestimo(EmprestimoDTO novoEmprestimo){

        if(validaNumeroParcela(novoEmprestimo.getNumeroParcelas()))
            throw new IllegalValueException("A quantidade de parcelas excede o permitido");

        String url = "http://localhost:8080/api/pessoa/internal/"+novoEmprestimo.getIdentificadorSolicitante();
        Emprestimo emprestimo = new Emprestimo();
        LimitsDTO response = restTemplate.getForObject(url,LimitsDTO.class);

        if(validaValorParcela(novoEmprestimo.getValorEmprestimo(),novoEmprestimo.getNumeroParcelas(),response.getValMin()))
            throw new IllegalValueException("Valor minimo não atingido");

        if(validaValorTotal(novoEmprestimo.getValorEmprestimo(),response.getValTotal()))
            throw new IllegalValueException("Valor total permitido foi ultrapassado");

        emprestimo.setValorEmprestimo(novoEmprestimo.getValorEmprestimo());
        emprestimo.setNumeroParcelas(novoEmprestimo.getNumeroParcelas());
        emprestimo.setStatusPagamento("Aberto");
        emprestimo.setIdPessoa(response.getIdPessoa());
        emprestimo.setDataCriacao(novoEmprestimo.getDataCriacao());
        return repository.save(emprestimo);

    }
    private boolean validaValorTotal(double valorEstimado, double valorPermitido){
        return valorEstimado < valorPermitido;
    }

    private boolean validaValorParcela(double valorEstimado, int numeroParcelas, double valorPermitido){
        double valorParcela = valorEstimado / numeroParcelas;
        return  valorParcela > valorPermitido;
    }

    private boolean validaNumeroParcela(int numeroParcelas){
        return numeroParcelas < 24;
    }

}


