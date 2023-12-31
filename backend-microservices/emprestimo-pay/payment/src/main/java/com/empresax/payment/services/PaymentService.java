package com.empresax.payment.services;

import com.empresax.payment.exceptions.CustomException;
import com.empresax.payment.models.Emprestimo;
import com.empresax.payment.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Emprestimo realizarPagamento(int id){
        try {
            Emprestimo entidade = repository.findById(id).orElseThrow();
            entidade.setStatusPagamento("pago");
            return repository.save(entidade);
        }catch (Exception e){
            throw  new CustomException("Empréstimo com o identificador: " + id + "não encontrada");
        }

    }

}
