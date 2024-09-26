package com.castelo.caixa.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.castelo.caixa.modelo.Operacao;

public interface OperacaoRepository extends JpaRepository<Operacao, Long>{
        Optional<Operacao> findByNome(String nome);

    //@Transactional(readOnly = true)  
    @Query(value = "select o.id from operacao o where o.operacao = ?1", nativeQuery = true)
    Long findByIdNome(String operacao);
}
