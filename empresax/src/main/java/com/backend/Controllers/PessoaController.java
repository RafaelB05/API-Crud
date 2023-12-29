package com.backend.Controllers;


import com.backend.Data.Dto.PessoaDTO;
import com.backend.Models.Pessoa;
import com.backend.Services.PessoaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    @Autowired
    private PessoaServices services;

    @GetMapping(value ="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa findPessoa(@PathVariable(value = "id")int id){
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
    public  Pessoa updatePessoa(@PathVariable(value = "id")int id, @RequestBody PessoaDTO pessoa){
        return services.updatePessoa(id,pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public  void deletePessoa(@PathVariable(value = "id")int id){
        services.deletePessoa(id);
    }

}
