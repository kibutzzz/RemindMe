package com.ifsul.remindme;

public class Group {
    private String nome;
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Group(String nome, String descricao) {

        this.nome = nome;
        this.descricao = descricao;
    }
}
