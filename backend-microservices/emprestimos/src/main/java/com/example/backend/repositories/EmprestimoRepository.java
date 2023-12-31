package com.example.backend.repositories;

import com.example.backend.models.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo,Integer>{}
