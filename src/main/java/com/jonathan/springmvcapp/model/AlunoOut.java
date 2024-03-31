package com.jonathan.springmvcapp.model;
import java.util.UUID;

import com.jonathan.springmvcapp.controller.AlunoController;
import com.jonathan.springmvcapp.repository.SORepostory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;


public class AlunoOut {

    private Integer id;
    private Pessoa pessoa;
    private Avatar avatar;
    private Curso curso;
    private String linguagem;
    private List<SistemaOperacional> sistemaOperacional;
    
    public AlunoOut(Integer id, Pessoa pessoa, Avatar avatar, Curso curso, String linguagem, 
            List<SistemaOperacional> sistemaOperacional) {
        this.id = id;
        this.pessoa = pessoa;
        this.avatar = avatar;
        this.curso = curso;
        this.linguagem = linguagem;
        this.sistemaOperacional = sistemaOperacional;
    }
    public AlunoOut() {
    }
    public AlunoOut(Integer id, Pessoa pessoa, Curso curso, String linguagem, 
            List<SistemaOperacional> sistemaOperacional) {
        this.id = id;
        this.pessoa = pessoa;
        this.curso = curso;
        this.linguagem = linguagem;
        this.sistemaOperacional = sistemaOperacional;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public Avatar getAvatar() {
        return avatar;
    }
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public String getLinguagem() {
        return linguagem;
    }
    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }
    
    public List<SistemaOperacional> getSistemaOperacional() {
        return sistemaOperacional;
    }
    public void setSistemaOperacional(List<SistemaOperacional> sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }


    @Override
    public String toString() {
        return "AlunoOut [id=" + id + ", pessoa=" + pessoa + ", avatar=" + avatar + ", curso=" + curso + ", linguagem="
                + linguagem + ", sistemaOperacional=" + sistemaOperacional + "]";
    }
}
