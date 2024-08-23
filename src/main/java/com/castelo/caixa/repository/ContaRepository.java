package com.castelo.caixa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.castelo.caixa.modelo.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    
}