package com.jonathan.springmvcapp.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "avatar")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idAluno;
    
    @Column(unique = true)
    private String nomeFantasia;

    public Avatar() {
    }

    public Avatar(Integer id, Integer idAluno, String nomeFantasia) {
        this.id = id;
        this.idAluno = idAluno;
        this.nomeFantasia = nomeFantasia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public String toString() {
        return "Avatar [id=" + id + ", idAluno=" + idAluno + ", nomeFantasia=" + nomeFantasia + "]";
    }

    
    
}
