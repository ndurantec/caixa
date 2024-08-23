package com.castelo.caixa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.castelo.caixa.repository.ContaRepository;

@RestController
@RequestMapping(value = "/conta")
    public class ContaController {

    @Autowired
    private ContaRepository contaRepository;


    @GetMapping (value = "/conta")
    public String imprimirConta(){
        return "Chegou no servidor1";
    }
   
    @PostMapping(value = "/inserirConta")
        public void inserirConta() {
    
    }

    @PutMapping(value = "/atualizarConta")
        public void listarConta(){

    }

    @DeleteMapping(value = "/deletarConta")
        public void deletarConta(){

    }

    @GetMapping(value = "/findall")
    public List findall(){
        return contaRepository.findAll();
    }
    




}