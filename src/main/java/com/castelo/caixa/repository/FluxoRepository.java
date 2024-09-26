package com.castelo.caixa.repository;

import java.util.Optional;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.castelo.caixa.modelo.Fluxo;


public interface FluxoRepository extends JpaRepository<Fluxo, Long>{
    List<Fluxo> findByData(LocalDate data);

    List<Fluxo> findByDataBetweenAndOperacaoNome(Date dataInicio, Date dataFim, String operacao);

}
