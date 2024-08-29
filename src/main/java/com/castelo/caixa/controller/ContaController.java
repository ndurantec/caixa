package com.castelo.caixa.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.castelo.caixa.dto.ContaDto;
import com.castelo.caixa.modelo.Conta;
import com.castelo.caixa.repository.ContaRepository;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {

    @Autowired
    ContaRepository contaRepository;

    @GetMapping (value = "/findAll")
    public String imprimir(){
        return "Chegou no servidor1";
    }
   
    @PostMapping(value = "/inserir")
        public void inserir() {
    
    }

    @PutMapping(value = "/atualizar")
        public void listar(){

    }

    @DeleteMapping(value = "/deletar")
        public void deletar(){

    }


    @PostMapping(value = "/insert")
    public ResponseEntity<Conta> insert(@RequestBody ContaDto contaDto) {
            Conta conta = contaDto.novoConta();
            contaRepository.save(conta);      

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(conta.getId()).toUri();

            return ResponseEntity.created(uri).body(conta);
        
    }
    
}