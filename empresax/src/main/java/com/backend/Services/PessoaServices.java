package com.backend.Services;

import com.backend.Data.Dto.PessoaDTO;
import com.backend.Enum.TipoIdentificador;
import com.backend.Models.Pessoa;
import com.backend.Repositories.PessoaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PessoaServices {

    private TipoIdentificador verificaIdentificador(String id){
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

    public Pessoa findPessoa(String identificador){
        return repositories.findById(identificador).orElseThrow();
    }

    public List<Pessoa> findAllPessoas(){
        return  repositories.findAll();
    }

    public Pessoa createPessoa(PessoaDTO pessoa){
        Pessoa entidade = new Pessoa();
        TipoIdentificador identificadorPessoa = verificaIdentificador(pessoa.getIdentificador());
        entidade.setNome(pessoa.getNome());
        entidade.setIdentificador(pessoa.getIdentificador());
        entidade.setDataNascimento(pessoa.getDataNascimento());
        entidade.setTipoIdentificador(identificadorPessoa.getTipo());
        entidade.setValTotal(identificadorPessoa.retornaValTotal());
        entidade.setValParcela(identificadorPessoa.retornaValMin());

        return repositories.save(entidade);

    }

    public Pessoa updatePessoa(String identificador,PessoaDTO pessoa){
        Pessoa entidade = repositories.findById(identificador).orElseThrow();

        entidade.setNome(pessoa.getNome());
        entidade.setDataNascimento(pessoa.getDataNascimento());

        return repositories.save(entidade);
    }

    public void deletePessoa(String identificador){
        Pessoa entidade = repositories.findById(identificador).orElseThrow();
        repositories.delete(entidade);
    }

}
