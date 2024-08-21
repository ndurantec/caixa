package com.castelo.caixa.dto;

import java.sql.Date;

import com.castelo.caixa.modelo.Conta;
import com.castelo.caixa.modelo.Operacao;

public class FluxoDto {
    
    private Conta conta;
    private Date data;
    private Operacao operacao;
    private double valor;
    private String descricao;
    
    @Deprecated
    public FluxoDto() {
    }

    public FluxoDto(Conta conta, Date data, Operacao operacao, double valor, String descricao) {
        this.conta = conta;
        this.data = data;
        this.operacao = operacao;
        this.valor = valor;
        this.descricao = descricao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
