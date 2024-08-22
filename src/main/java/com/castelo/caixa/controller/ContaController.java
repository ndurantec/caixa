package com.castelo.caixa.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {

    @GetMapping (value = "/conta")
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


}


