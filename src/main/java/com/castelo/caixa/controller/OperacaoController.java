package com.castelo.caixa.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.castelo.caixa.dto.OperacaoDto;
import com.castelo.caixa.modelo.Conta;
import com.castelo.caixa.modelo.Operacao;
import com.castelo.caixa.repository.OperacaoRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/operacao")
public class OperacaoController {

    @Autowired
    private OperacaoRepository operacaoRepository;

    @GetMapping (value = "/findAll")
    public List findAll(){
        return operacaoRepository.findAll();
    }  

        @PostMapping("/findByNome")
    public ResponseEntity<Long> buscarContaPorNome(@RequestBody OperacaoDto operacaoDto) {
        Optional<Operacao> operacao = operacaoRepository.findByNome(operacaoDto.getNome());
        Operacao operacaoObjeto = operacao.get();
        System.out.println(operacaoObjeto.toString());
        return operacao.map(c -> ResponseEntity.ok(c.getId()))
                    .orElse(ResponseEntity.notFound().build());
    }
        
    @PostMapping (value = "/insert")
    public ResponseEntity<Operacao> insert(@RequestBody OperacaoDto operacaoDto) {

        String regex = ".*\\d.*";
        if (operacaoDto.getNome().matches(regex)) {
            return ResponseEntity.badRequest().header("Error-message", "O nome da conta não pode conter numeros").build();
        }

        if(operacaoRepository.existsByNome(operacaoDto.getNome())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                    .body(null);
        }else{
                Operacao operacao = operacaoDto.novOperacao();
                operacaoRepository.save(operacao);
                
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(operacao.getId())
                                    .toUri();

                                    return ResponseEntity.created(uri).body(operacao);
            }
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

        @GetMapping("/existe")
            public ResponseEntity<Boolean> verificarNomeExite(@RequestParam String nome){
                boolean existe = operacaoRepository.existsByNome(nome);
                return ResponseEntity.ok(existe);
            }
}