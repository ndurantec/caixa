package com.castelo.caixa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castelo.caixa.modelo.Operacao;

public interface OperacaoRepository extends JpaRepository<Operacao, Long>{
    
}
