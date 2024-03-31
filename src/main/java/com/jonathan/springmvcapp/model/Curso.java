package com.jonathan.springmvcapp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "curso")
public class Curso {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private List<String> disciplinas;


    public Curso(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
    public Curso() {
    }
    public Curso(Integer id, String nome, String descricao, List<String> disciplinas) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.disciplinas = disciplinas;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public List<String> getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(List<String> disciplinas) {
        this.disciplinas = disciplinas;
    }
    @Override
    public String toString() {
        return "Curso [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", disciplinas=" + disciplinas + "]";
    }
   

   
}

