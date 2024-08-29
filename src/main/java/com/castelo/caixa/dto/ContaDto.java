package com.castelo.caixa.dto;

import com.castelo.caixa.modelo.Conta;

public class ContaDto {
        private Long id;
        private String nome;
        private String descricao;

        @Deprecated
        public ContaDto() {
        }

        public ContaDto(Long id, String nome, String descricao) {
            this.id = id;
            this.nome = nome;
            this.descricao = descricao;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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
    
        public Conta novoConta(){
            return new Conta(null, nome, descricao);
        }
}
