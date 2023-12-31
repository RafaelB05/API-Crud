package com.example.backend.controllers;

import com.example.backend.data.dto.EmprestimoDTO;
import com.example.backend.models.Emprestimo;
import com.example.backend.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api/emprestimo/realizar")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Emprestimo realizarEmprestimo(@RequestBody EmprestimoDTO novoEmprestimo){return service.realizarEmprestimo(novoEmprestimo);}

}
