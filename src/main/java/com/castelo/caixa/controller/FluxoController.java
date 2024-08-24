package com.castelo.caixa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castelo.caixa.repository.FluxoRepository;

@RestController
@RequestMapping(value = "/fluxo")
public class FluxoController {
    
    @Autowired
    private FluxoRepository fluxoRepository;

    @GetMapping (value = "/findAll")
    public List findAll(){
        return fluxoRepository.findAll();
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


}
