package com.backend.repositories;

import com.backend.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepositories extends JpaRepository<Pessoa,Integer> {
    @Query("SELECT e FROM Pessoa e WHERE e.identificador = :identificador")
    Pessoa findPessoaByCPF(@Param("identificador") String identificador);
}
