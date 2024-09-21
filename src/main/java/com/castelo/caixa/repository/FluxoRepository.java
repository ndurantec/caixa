package com.castelo.caixa.repository;

import java.util.Optional;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.castelo.caixa.modelo.Fluxo;


public interface FluxoRepository extends JpaRepository<Fluxo, Long>{
        Optional<Fluxo> findByData(Date data);

    //@Transactional(readOnly = true)  
    @Query(value = "select f.id from fluxo f where f.fluxo = ?1", nativeQuery = true)
    Long findByIdNome(Date data);
}

