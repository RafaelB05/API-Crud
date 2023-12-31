package com.backend.controllers;


import com.backend.data.dto.LimitsDTO;
import com.backend.data.dto.PessoaDTO;
import com.backend.models.Pessoa;
import com.backend.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService services;

    @GetMapping(value ="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa findPessoa(@PathVariable(value = "id")Integer id){
        return services.findPessoa(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pessoa> findAllPessoas(){
        return services.findAllPessoas();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa createPessoa(@RequestBody PessoaDTO pessoa){
        return services.createPessoa(pessoa);
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public  Pessoa updatePessoa(@PathVariable(value = "id")Integer id, @RequestBody PessoaDTO pessoa){
        return services.updatePessoa(id,pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public  void deletePessoa(@PathVariable(value = "id")Integer id){
        services.deletePessoa(id);
    }

    @GetMapping(value = "/internal/{identificador}",produces = MediaType.APPLICATION_JSON_VALUE)
    public LimitsDTO findPessoaLimits(@PathVariable(value = "identificador")String identificador){
        return services.findPessoaLimits(identificador);
    }

}
