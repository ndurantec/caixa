package com.castelo.caixa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FluxoController {
     @GetMapping (value = "/fluxo")
    public String imprimirFluxo(){
        return "Chegou no servidor2";
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
