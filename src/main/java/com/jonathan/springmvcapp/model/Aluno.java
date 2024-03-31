package com.jonathan.springmvcapp.model;
import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;


@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer pessoa;
    private Integer avatar;
    private Integer curso;
    private String linguagem;
    private List<Integer> sistemaOperacional;
    
    public Aluno() {
    }
    public Aluno(Integer id, Integer pessoa, Integer avatar, Integer curso, String linguagem,
            List<Integer> sistemaOperacional) {
        this.id = id;
        this.pessoa = pessoa;
        this.avatar = avatar;
        this.curso = curso;
        this.linguagem = linguagem;
        this.sistemaOperacional = sistemaOperacional;
    }
    public Aluno(Integer pessoa, Integer curso, String linguagem, List<Integer> sistemaOperacional) {
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
    public Integer getPessoa() {
        return pessoa;
    }
    public void setPessoa(Integer pessoa) {
        this.pessoa = pessoa;
    }
    public Integer getAvatar() {
        return avatar;
    }
    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }
    public Integer getCurso() {
        return curso;
    }
    public void setCurso(Integer curso) {
        this.curso = curso;
    }
    public String getLinguagem() {
        return linguagem;
    }
    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }
    public List<Integer> getSistemaOperacional() {
        return sistemaOperacional;
    }
    public void setSistemaOperacional(List<Integer> sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }
    @Override
    public String toString() {
        return "Aluno [id=" + id + ", pessoa=" + pessoa + ", avatar=" + avatar + ", curso=" + curso + ", linguagem="
                + linguagem + ", sistemaOperacional=" + sistemaOperacional + "]";
    }
    


}
