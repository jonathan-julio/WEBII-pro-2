package com.jeanlima.springmvcapp.model;

import java.util.List;

public class SoLista {
    private String SO;
    private List<Aluno> alunos;
    public SoLista(String sO, List<Aluno> aluno) {
        this.SO = sO;
        this.alunos = aluno;
    }

    public SoLista() {
    }
    

    public List<Aluno> getAluno() {
        return alunos;
    }

    public void setAluno(List<Aluno> aluno) {
        this.alunos = aluno;
    }
    public String getSO() {
        return SO;
    }

    public void setSO(String SO) {
        this.SO = SO;
    }
    
    
}
