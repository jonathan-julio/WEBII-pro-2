package com.jeanlima.springmvcapp.model;

public class Curso {
    private String nome;
    private String descricao;
   
    public Curso(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Curso() {
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
}

