package com.castelo.caixa.dto;

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
