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

    @GetMapping(value ="/{identificador}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa findPessoa(@PathVariable(value = "identificador")String identificador){
        return services.findPessoa(identificador);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pessoa> findAllPessoas(){
        return services.findAllPessoas();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa createPessoa(@RequestBody PessoaDTO pessoa){
        return services.createPessoa(pessoa);
    }

    @PutMapping(value = "/{identificador}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public  Pessoa updatePessoa(@PathVariable(value = "identificador")String identificador, @RequestBody PessoaDTO pessoa){
        return services.updatePessoa(identificador,pessoa);
    }

    @DeleteMapping(value = "/{identificador}")
    public  void deletePessoa(@PathVariable(value = "identificador")String identificador){
        services.deletePessoa(identificador);
    }

}
