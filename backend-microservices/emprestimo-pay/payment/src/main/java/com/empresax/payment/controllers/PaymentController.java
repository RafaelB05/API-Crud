package com.empresax.payment.controllers;

import com.empresax.payment.models.Emprestimo;
import com.empresax.payment.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/emprestimo/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Emprestimo realizarPagamento(@PathVariable(value = "id")int id){
        return  service.realizarPagamento(id);
    }
}
