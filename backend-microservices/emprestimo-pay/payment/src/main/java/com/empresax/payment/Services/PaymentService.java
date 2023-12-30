package com.empresax.payment.Services;

import com.empresax.payment.Exceptions.CustomException;
import com.empresax.payment.Models.Emprestimo;
import com.empresax.payment.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Emprestimo realizarPagamento(int id){
        try {
            Emprestimo entidade = repository.findById(id).orElseThrow();
            entidade.setStatus_pagamento("pago");
            return repository.save(entidade);
        }catch (Exception e){
            throw  new CustomException("Empréstimo com o identificador: " + id + "não encontrada");
        }

    }

}
