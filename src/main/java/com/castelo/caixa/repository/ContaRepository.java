package com.castelo.caixa.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.castelo.caixa.modelo.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
    Optional<Conta> findByNome(String nome);

    //@Transactional(readOnly = true)  
    @Query(value = "select u.id from conta u where u.conta = ?1", nativeQuery = true)
    Long findByIdNome(String conta);

    boolean existsByNome(String nome);
}