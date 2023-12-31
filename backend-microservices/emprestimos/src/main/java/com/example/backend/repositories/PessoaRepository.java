package com.example.backend.repositories;


import com.example.backend.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Integer> {
    Optional<Pessoa>findByIdentificador(String identificador);
}
