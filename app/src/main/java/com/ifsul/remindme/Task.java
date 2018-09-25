package com.ifsul.remindme;

public class Task {
    private String nome;
    private String desricao;
    private String key;
    private String limite;

    public Task() {
    }

    public Task(String nome, String desricao, String limite) {
        this.nome = nome;
        this.desricao = desricao;
        this.limite = limite;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public String getDesricao() {
        return desricao;
    }

    public String getLimite() {
        return limite;
    }


    @Override
    public String toString() {
        String str = "key: " + this.key +
                "nome: " + this.nome +
                "descricao: " + this.desricao;
        return str;
    }


}
