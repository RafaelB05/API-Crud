package com.empresax.payment.repositories;

import com.empresax.payment.models.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Emprestimo,Integer> {}
