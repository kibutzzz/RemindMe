package com.ifsul.remindme;

public class Task {
    private String nome;
    private String desricao;
    private String limite;
    private String key;

    public Task() {
    }

    public Task(String nome, String desricao, int dia, int mes, int ano) {
        this.nome = nome;
        this.desricao = desricao;
        this.limite = dia + "/" + mes + "/" + ano;

    }

    public Task(String nome, String descricao) {
        this.nome = nome;
        this.desricao = descricao;
        this.limite = null;
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
