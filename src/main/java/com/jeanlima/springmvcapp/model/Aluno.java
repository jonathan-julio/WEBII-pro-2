package com.jeanlima.springmvcapp.model;
import java.util.UUID;

import java.util.List;

public class Aluno {

    private String id;
    private String primeiroNome;
    private String ultimoNome;
    private String curso;
    private String linguagem;
    private String email;
    private List<String> sistemasOperacionais;

    public Aluno() {
    }

    public Aluno(String id , String primeiroNome, String ultimoNome, String curso, String linguagem, String email) {
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.curso = curso;
        this.linguagem = linguagem;
        this.email = email;
        System.out.println("UUID.randomUUID(): "+ UUID.randomUUID());
    }

    public Aluno(String id,String primeiroNome, String ultimoNome, String curso, String linguagem, String email,
            List<String> sistemasOperacionais) {
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.curso = curso;
        this.linguagem = linguagem;
        this.email = email;
        this.sistemasOperacionais = sistemasOperacionais;
        System.out.println("UUID.randomUUID(): "+ UUID.randomUUID());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", primeiroNome=" + primeiroNome + ", curso=" + curso + ", ultimoNome=" + ultimoNome + ", email=" + email
                + ", sistemasOperacionais=" + sistemasOperacionais + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((primeiroNome == null) ? 0 : primeiroNome.hashCode());
        result = prime * result + ((ultimoNome == null) ? 0 : ultimoNome.hashCode());
        result = prime * result + ((curso == null) ? 0 : curso.hashCode());
        result = prime * result + ((linguagem == null) ? 0 : linguagem.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((sistemasOperacionais == null) ? 0 : sistemasOperacionais.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Aluno other = (Aluno) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (primeiroNome == null) {
            if (other.primeiroNome != null)
                return false;
        } else if (!primeiroNome.equals(other.primeiroNome))
            return false;
        if (ultimoNome == null) {
            if (other.ultimoNome != null)
                return false;
        } else if (!ultimoNome.equals(other.ultimoNome))
            return false;
        if (curso == null) {
            if (other.curso != null)
                return false;
        } else if (!curso.equals(other.curso))
            return false;
        if (linguagem == null) {
            if (other.linguagem != null)
                return false;
        } else if (!linguagem.equals(other.linguagem))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    public void setSistemasOperacionais(List<String> sistemasOperacionais) {
        this.sistemasOperacionais = sistemasOperacionais;
    }

    public List<String> getSistemasOperacionais() {
        return sistemasOperacionais;
    }

}
