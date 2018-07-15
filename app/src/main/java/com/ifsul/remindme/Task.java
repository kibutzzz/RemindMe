package com.ifsul.remindme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private String nome;
    private String desricao;
    private Date limite;

    public Task(String nome, String desricao, String limite) {
        this.nome = nome;
        this.desricao = desricao;
        try {
            this.limite = new SimpleDateFormat("dd/MM/yyyy").parse(limite);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    public String getDesricao() {
        return desricao;
    }

    public Date getLimite() {
        return limite;
    }
}
