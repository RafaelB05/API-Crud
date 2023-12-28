package com.backend.Services;

import com.backend.Repositories.PessoaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServices {

    @Autowired
    private PessoaRepositories repositories;



}
