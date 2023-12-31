package com.backend.services;

import com.backend.data.dto.LimitsDTO;
import com.backend.data.dto.PessoaDTO;
import com.backend.exceptions.DuplicatedEntityException;
import com.backend.exceptions.EntityNotFoundException;
import com.backend.exceptions.InvalidIdException;
import com.backend.models.Pessoa;
import com.backend.repositories.PessoaRepository;
import com.backend.strategy.*;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

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

    private Pessoa mapearPessoa(PessoaDTO pessoa){
        Pessoa entidade = new Pessoa();
        entidade.setNome(pessoa.getNome());
        entidade.setIdentificador(pessoa.getIdentificador());
        entidade.setDataNascimento(pessoa.getDataNascimento());
        entidade.setTipoIdentificador(contexto.retornaTipoIdentificador());
        entidade.setValTotal(contexto.retornaValTotal());
        entidade.setValParcela(contexto.retornaValMin());

        return entidade;
    }

    public Pessoa findPessoa(Integer id){
        return repository.findById(id).orElseThrow(()-> new EntityNotFoundException("A pessoa de identificador:" + id +" não foi localizada"));
    }

    public List<Pessoa> findAllPessoas(){
        return  repository.findAll();
    }

    public Pessoa createPessoa(PessoaDTO pessoa){
        Pessoa entidade = mapearPessoa(pessoa);
        verificaTipoIdentificador(pessoa.getIdentificador());

        if(repository.findByIdentificador(pessoa.getIdentificador()).isPresent())
            throw  new DuplicatedEntityException("O identificador informado já está cadastrado");

        if(contexto.validaIdentificador(entidade.getIdentificador()))
            return repository.save(entidade);

        else
            throw new InvalidIdException("O identificador digitado possui valor invalido");
    }

    public Pessoa updatePessoa(Integer id,PessoaDTO pessoa){
        Pessoa entidade = repository.findById(id).orElseThrow(()->new EntityNotFoundException("Pessoa com o id: " + id + "não encontrada"));
        entidade.setNome(pessoa.getNome());
        entidade.setDataNascimento(pessoa.getDataNascimento());
        return repository.save(entidade);
    }

    public void deletePessoa(Integer id){
        Pessoa entidade = repository.findById(id).orElseThrow(()->new EntityNotFoundException("Pessoa com o id: " + id + "não encontrada"));
        repository.delete(entidade);

    }

    public LimitsDTO findPessoaLimits(String identificador){
        Pessoa entidade = repository.findByIdentificador(identificador).orElseThrow(()->new EntityNotFoundException("Pessoa com o identificador: " + identificador + "não encontrada"));
        return new LimitsDTO(entidade.getId(),entidade.getValTotal(),entidade.getValParcela());
    }
}
