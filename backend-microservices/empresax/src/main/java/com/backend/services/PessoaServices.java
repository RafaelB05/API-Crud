package com.backend.services;

import com.backend.data.dto.LimitsDTO;
import com.backend.data.dto.PessoaDTO;
import com.backend.exceptions.CustomException;
import com.backend.models.Pessoa;
import com.backend.repositories.PessoaRepositories;
import com.backend.strategy.*;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Builder
public class PessoaServices {
    @Autowired
    private  PessoaRepositories repositories;

    private static Context contexto = new Context();
    private void verificaTipoIdentificador(String id){
        switch (id.length()) {
            case 11 -> contexto.setStrategy(new ConcreteStrategyCpf());
            case 14 -> contexto.setStrategy(new ConcreteStrategyCnpj());
            case 8 -> contexto.setStrategy(new ConcreteStrategyEu());
            case 10 -> contexto.setStrategy(new ConcreteStrategyAp());
            default -> throw new RuntimeException();
        }
    }

    public Pessoa findPessoa(int id) throws NoSuchElementException{
        try{
            return repositories.findById(id).orElseThrow();
        }
        catch (Exception e){
            throw  new CustomException("Pessoa com o id: " + id + "não encontrada");
        }
    }

    public List<Pessoa> findAllPessoas(){
        return  repositories.findAll();
    }

    public Pessoa createPessoa(PessoaDTO pessoa){
        Pessoa entidade = new Pessoa();
        verificaTipoIdentificador(pessoa.getIdentificador());
        entidade.setNome(pessoa.getNome());
        entidade.setIdentificador(pessoa.getIdentificador());
        entidade.setDataNascimento(pessoa.getDataNascimento());
        entidade.setTipoIdentificador(contexto.retornaTipoIdentificador());
        entidade.setValTotal(contexto.retornaValTotal());
        entidade.setValParcela(contexto.retornaValMin());

        if(contexto.validaIdentificador(entidade.getIdentificador())){
            return repositories.save(entidade);
        }
        else
            throw new RuntimeException();
    }

    public Pessoa updatePessoa(int id,PessoaDTO pessoa){
        try{
            Pessoa entidade = repositories.findById(id).orElseThrow();
            entidade.setNome(pessoa.getNome());
            entidade.setDataNascimento(pessoa.getDataNascimento());

            return repositories.save(entidade);
        }catch (Exception e){
            throw new CustomException("Pessoa com o id: " + id + "não encontrada");
        }
    }

    public void deletePessoa(int id){
        try{
            Pessoa entidade = repositories.findById(id).orElseThrow();
            repositories.delete(entidade);
        }catch (Exception e){
            throw new CustomException("Pessoa com o id: " + id + "não encontrada");
        }

    }

    public LimitsDTO findPessoaLimits(String identificador){
        try {
            Pessoa entidade = repositories.findPessoaByCPF(identificador);
            return new LimitsDTO(entidade.getId(),entidade.getValTotal(),entidade.getValParcela());

        }catch (Exception e){
            throw  new CustomException("Pessoa com o identificador: " + identificador + "não encontrada");
        }
    }
}
