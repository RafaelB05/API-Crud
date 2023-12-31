package com.backend.repositories;

import com.backend.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Integer> {
    Optional<Pessoa>findByIdentificador(String identificador);
}
