package com.ifsul.remindme;

import java.util.List;

public class Group {
    private String nome;
    private String descricao;
    private List<String> participates;

    Group(String nome, String descricao, List<String> participates) {
        this.nome = nome;
        this.descricao = descricao;
        this.participates = participates;
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

    public List<String> getParticipates() {
        return participates;
    }

    public void setParticipates(List<String> participates) {
        this.participates = participates;
    }
}
