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

    public Group(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }
    public Group() {
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<String> getParticipates() {
        return participates;
    }
}
