package com.castelo.caixa.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.castelo.caixa.modelo.Conta;
import com.castelo.caixa.repository.ContaRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/conta")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;


    @GetMapping (value = "/findAll")
    public List findAll(){
        return contaRepository.findAll();
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<Conta> insert(@RequestBody ContaDto contaDto) {
            Conta conta = contaDto.novoConta();
            contaRepository.save(conta);      

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                .buildAndExpand(conta.getId())
                                .toUri();

            return ResponseEntity.created(uri).body(conta);
        
    }
    

    @GetMapping(value = "/{id}")
    public ResponseEntity<Conta> findById(@PathVariable Long id){
        return contaRepository.findById(id)            
            .map(registro ->ResponseEntity.ok().body(registro))            
            .orElse(ResponseEntity.notFound().build()); 

    }

    @PostMapping("/findByNome")
    public ResponseEntity<Long> buscarContaPorNome(@RequestBody ContaDto contaDto) {
        Optional<Conta> conta = contaRepository.findByNome(contaDto.getNome());
        Conta contaObjeto = conta.get();
        System.out.println(contaObjeto.toString());
        return conta.map(c -> ResponseEntity.ok(c.getId()))
                    .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> updateConta(@PathVariable Long id, @RequestBody Conta updatedConta) {
        return contaRepository.findById(id)
                .map(conta -> {
                    conta.setNome(updatedConta.getNome());
                    conta.setDescricao(updatedConta.getDescricao());
                    contaRepository.save(conta);
                    return ResponseEntity.ok(conta);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteConta(@PathVariable Long id) {
        return contaRepository.findById(id)
                .map(conta -> {
                    contaRepository.delete(conta);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}