package com.empresax.payment.Repositories;

import com.empresax.payment.Models.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Emprestimo,Integer> {}
