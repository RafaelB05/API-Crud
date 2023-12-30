package com.backend.Services;

import com.backend.Data.Dto.LimitsDTO;
import com.backend.Data.Dto.PessoaDTO;
import com.backend.Enum.TipoIdentificador;
import com.backend.Exceptions.CustomException;
import com.backend.Models.Pessoa;
import com.backend.Repositories.PessoaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PessoaServices {

    private TipoIdentificador verificaTipoIdentificador(String id){
        TipoIdentificador  identificador = null;

        switch (id.length()){
            case 11:
                identificador =TipoIdentificador.CPF;
                break;
            case 14:
                identificador = TipoIdentificador.CNPJ;
                break;

            case 8:
                identificador = TipoIdentificador.EU;
                break;

            case 10:
                identificador = TipoIdentificador.AP;
                break;

            default:
                break;
        }

        return  identificador;
    }

    @Autowired
    private  PessoaRepositories repositories;

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
        TipoIdentificador identificadorPessoa = verificaTipoIdentificador(pessoa.getIdentificador());
        entidade.setNome(pessoa.getNome());
        entidade.setIdentificador(pessoa.getIdentificador());
        entidade.setDataNascimento(pessoa.getDataNascimento());
        entidade.setTipoIdentificador(identificadorPessoa.getTipo());
        entidade.setValTotal(identificadorPessoa.retornaValTotal());
        entidade.setValParcela(identificadorPessoa.retornaValMin());

        if(identificadorPessoa.validaIdentificador(entidade.getIdentificador())){
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