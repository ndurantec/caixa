package com.castelo.caixa.dto;

import com.castelo.caixa.modelo.Operacao;

public class OperacaoDto {
    private Long id;
    private String nome;

    @Deprecated
    public OperacaoDto() {
    }

    public OperacaoDto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
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
    
    public Operacao novOperacao(){
        return new Operacao(null, nome);
    }
}
