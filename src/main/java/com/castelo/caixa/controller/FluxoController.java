 package com.castelo.caixa.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.castelo.caixa.dto.ContaDto;
import com.castelo.caixa.dto.FluxoDto;
import com.castelo.caixa.modelo.Conta;
import com.castelo.caixa.modelo.Fluxo;
import com.castelo.caixa.modelo.Operacao;
import com.castelo.caixa.repository.ContaRepository;
import com.castelo.caixa.repository.FluxoRepository;
import com.castelo.caixa.repository.OperacaoRepository;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/fluxo")
public class FluxoController {

    @Autowired
    private FluxoRepository fluxoRepository;

    @Autowired
    private OperacaoRepository operacaoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping (value = "/findAll")
    public List findAll(){
        return fluxoRepository.findAll();
    }

    @PostMapping(value = "/insert")
        public ResponseEntity<Fluxo> insert(@RequestBody FluxoDto fluxoDto) {        
           Fluxo fluxo = fluxoDto.novoFluxo();
           fluxoRepository.save(fluxo);


           URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(fluxo.getId())
                                .toUri();


            return ResponseEntity.created(uri).body(fluxo);
    }

    

    @GetMapping(value = "/{id}")
    public ResponseEntity<Fluxo> findById(@PathVariable Long id){
        return fluxoRepository.findById(id)
            .map(registro -> ResponseEntity.ok().body(registro))
                .orElse(ResponseEntity.notFound().build());

    }

@GetMapping("/buscarPorData")
    public ResponseEntity<List<Long>> buscarFluxosPorData(
        @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        
        // Busca os fluxos pela data e retorna apenas os IDs
        List<Long> ids = fluxoRepository.findByData(data)
                                        .stream()
                                        .map(Fluxo::getId)  // Obtém o ID de cada fluxo
                                        .collect(Collectors.toList());
        return ResponseEntity.ok(ids);
    }


    @GetMapping("/buscarPorPeriodo")
    public List<FluxoDto> buscarFluxosPorPeriodo(@RequestParam("dataInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
                                                 @RequestParam("dataFim") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim,
                                                 @RequestParam("operacao") String operacao) {
        List<Fluxo> fluxos = fluxoRepository.findByDataBetweenAndOperacaoNome(dataInicio, dataFim, operacao);

        // Conversão de entidades para DTOs, passando os objetos completos Conta e Operacao
        List<FluxoDto> fluxosDto = fluxos.stream()
            .map(fluxo -> new FluxoDto(
                fluxo.getId(),
                fluxo.getConta(),          // Passando o objeto Conta completo
                fluxo.getData(),
                fluxo.getOperacao(),       // Passando o objeto Operacao completo
                fluxo.getValor(),
                fluxo.getDescricao()
            ))
            .collect(Collectors.toList());

        return fluxosDto;
}
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Fluxo> updateFluxo(@PathVariable Long id, @RequestBody Fluxo updatedFluxo) {
        return fluxoRepository.findById(id)
                .map(fluxo -> {
                    fluxo.setConta(contaRepository.findById(updatedFluxo.getConta().getId()).orElse(null));
                    fluxo.setOperacao(operacaoRepository.findById(updatedFluxo.getOperacao().getId()).orElse(null));
                    fluxo.setData(updatedFluxo.getData());
                    fluxo.setValor(updatedFluxo.getValor());
                    fluxo.setDescricao(updatedFluxo.getDescricao());
                    fluxoRepository.save(fluxo);
                    return ResponseEntity.ok(fluxo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFluxo(@PathVariable Long id) {
        return fluxoRepository.findById(id)
                .map(fluxo -> {
                    fluxoRepository.delete(fluxo);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void consultaEntrada(LocalDateTime data, String nome, Operacao origem) {
        
    }  

    @GetMapping("/somaValores")
    public ResponseEntity<Double> calcularSaldo(){

        Double totalEntradas = fluxoRepository.somaValores("Entrada");

        Double totalSaidas = fluxoRepository.somaValores("Saida");

        Double saldo = totalEntradas - totalSaidas;

        return ResponseEntity.ok(saldo);
    }

}
