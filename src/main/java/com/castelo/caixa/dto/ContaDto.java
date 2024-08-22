package com.castelo.caixa.dto;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class ContaDto {
        private String nome;
        private String descricao;
        
        @Deprecated
        public ContaDto() {
        }
    
        public ContaDto(String nome, String descricao) {
            this.nome = nome;
            this.descricao = descricao;
        }
    
        public String getNome() {
            return nome;
        }
    
        public void setNome(String nome) {
            this.nome = nome;
        }
    
        public String getDescricao() {
            return descricao;
        }
    
        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }
    
    







}
