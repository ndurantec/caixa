package com.castelo.caixa.dto;

public class OperacaoDto {
    
    private String nome;

    @Deprecated
    public OperacaoDto() {
    }

    public OperacaoDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
