package com.example.backend.services;

import com.example.backend.data.dto.EmprestimoDTO;
import com.example.backend.exceptions.EntityNotFoundException;
import com.example.backend.exceptions.IllegalValueException;
import com.example.backend.models.Emprestimo;
import com.example.backend.models.Pessoa;
import com.example.backend.repositories.EmprestimoRepository;
import com.example.backend.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Emprestimo realizarEmprestimo(EmprestimoDTO novoEmprestimo){

        Pessoa response = pessoaRepository.findByIdentificador(novoEmprestimo.getIdentificadorSolicitante()).orElseThrow(
                () ->new EntityNotFoundException("pessoa não cadastrada")
        );

        if(validaNumeroParcela(novoEmprestimo.getNumeroParcelas()))
            throw new IllegalValueException("A quantidade de parcelas excede o permitido");

        if(validaValorParcela(novoEmprestimo.getValorEmprestimo(),novoEmprestimo.getNumeroParcelas(),response.getValParcela()))
            throw new IllegalValueException("Valor minimo não atingido");

        if(validaValorTotal(novoEmprestimo.getValorEmprestimo(),response.getValTotal()))
            throw new IllegalValueException("Valor total permitido foi ultrapassado");

        Emprestimo emprestimo = mapearEmprestimo(novoEmprestimo,response);

        return emprestimoRepository.save(emprestimo);

    }
    private boolean validaValorTotal(double valorEstimado, double valorPermitido){
        return valorEstimado > valorPermitido;
    }

    private boolean validaValorParcela(double valorEstimado, int numeroParcelas, double valorPermitido){
        double valorParcela = valorEstimado / numeroParcelas;
        return  valorParcela < valorPermitido;
    }

    private boolean validaNumeroParcela(int numeroParcelas){
        return numeroParcelas > 24;
    }

    private Emprestimo mapearEmprestimo(EmprestimoDTO novoEmprestimo,Pessoa response){
        Emprestimo emprestimo= new Emprestimo();

        emprestimo.setValorEmprestimo(novoEmprestimo.getValorEmprestimo());
        emprestimo.setNumeroParcelas(novoEmprestimo.getNumeroParcelas());
        emprestimo.setStatusPagamento("Aberto");
        emprestimo.setIdPessoa(response.getId());
        emprestimo.setDataCriacao(novoEmprestimo.getDataCriacao());

        return emprestimo;
    }

}


