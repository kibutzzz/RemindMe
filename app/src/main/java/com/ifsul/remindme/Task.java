package com.ifsul.remindme;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private String nome;
    private String desricao;
    private Date limite;
    private String key;

    public Task(){};

    public Task(String nome, String desricao, String limite) {
        this.nome = nome;
        this.desricao = desricao;
        try {
            this.limite = new SimpleDateFormat("dd/MM/yyyy").parse(limite);
        } catch (ParseException e) {
            Log.i("Task", e.toString());
        }
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {

        this.key = key;
    }

    public Task(String nome, String descricao){
        this.nome = nome;
        this.desricao = descricao;
        this.limite = null;
    }

    public String getNome() {
        return nome;
    }

    public String getDesricao() {
        return desricao;
    }

    public String getLimite() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//
//        return simpleDateFormat.format(limite);
        return null;
    }


    @Override
    public String toString() {
        String str = "key: " + this.key +
                "nome: " + this.nome +
                "descricao: " +this.desricao;
        return str;
    }
}
