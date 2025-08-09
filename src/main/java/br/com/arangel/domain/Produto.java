package main.java.br.com.arangel.domain;

import main.java.br.com.arangel.annotation.TipoChave;

import java.math.BigDecimal;

public class Produto implements Persistente {

    @TipoChave("getCodigo")
    private String codigo;

    private String nome;

    private BigDecimal valor;

    private String descricao;

    public Produto(String codigo, String nome, BigDecimal valor, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
