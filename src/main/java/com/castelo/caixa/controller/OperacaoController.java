package com.castelo.caixa.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.castelo.caixa.dto.OperacaoDto;
import com.castelo.caixa.modelo.Operacao;
import com.castelo.caixa.repository.OperacaoRepository;

@RestController
@RequestMapping(value = "/operacao")
public class OperacaoController {

    @Autowired
    private OperacaoRepository operacaoRepository;

    @GetMapping (value = "/findAll")
    public List findAll(){
        return operacaoRepository.findAll();
    }

    @PostMapping (value = "/insert")
    public ResponseEntity<Operacao> insert(@RequestBody OperacaoDto operacaoDto) {
        Operacao operacao = operacaoDto.novOperacao();
        operacaoRepository.save(operacao);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(operacao.getId())
                            .toUri();

                            return ResponseEntity.created(uri).body(operacao);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Operacao> findById(@PathVariable Long id){
        return operacaoRepository.findById(id).map(registro -> ResponseEntity.ok().body(registro))
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Operacao> updateOperacao(@PathVariable Long id, @RequestBody Operacao updatedOperacao) {
        return operacaoRepository.findById(id)
                .map(operacao -> {
                    operacao.setNome(updatedOperacao.getNome());
                    operacaoRepository.save(operacao);
                    return ResponseEntity.ok(operacao);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOperacao(@PathVariable Long id) {
        return operacaoRepository.findById(id)
                .map(operacao -> {
                    operacaoRepository.delete(operacao);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
 