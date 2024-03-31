package com.jonathan.springmvcapp.model;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;




@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true)
    private String codigo;
    private String nome;
    private String ementa;
    
    public Disciplina(String codigo, String nome, String ementa) {
        this.codigo = codigo;
        this.nome = nome;
        this.ementa = ementa;
    }
    public Disciplina() {
    }
    public Disciplina(Integer id, String codigo, String nome, String ementa) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.ementa = ementa;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmenta() {
        return ementa;
    }
    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }
    @Override
    public String toString() {
        return "Disciplina [id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", ementa=" + ementa + "]";
    }

}
