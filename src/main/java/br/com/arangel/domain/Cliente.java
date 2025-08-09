package main.java.br.com.arangel.domain;

import main.java.br.com.arangel.annotation.TipoChave;

public class Cliente implements Persistente {

    @TipoChave("getCpf")
    private Long cpf;
    private String nome;
    private String cidade;
    private String estado;
    private String end;
    private Integer numero;
    private Long tel;

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getEnd() {
        return end;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public long getTel() {
        return tel;
    }
}
