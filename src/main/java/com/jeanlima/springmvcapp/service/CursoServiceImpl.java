package com.jeanlima.springmvcapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jeanlima.springmvcapp.model.Curso;

@Component
public class CursoServiceImpl implements CursoService {

    public List<Curso> cursos = new ArrayList<Curso>();

    public boolean init = false;

    

    @Autowired
    public void initCurso() {
        Curso curso1 = new Curso("BTI", "primeiro");
        Curso curso2 = new Curso("EngSoft", "segundo");
        Curso curso3 = new Curso("CC", "nunca");

       
        if (init == false) {
            cursos.add(curso1);
            cursos.add(curso2);
            cursos.add(curso3);
            init = true;
        }
    }
   
    @Override
    public boolean salvarCurso(Curso curso) {
        // Verificar se o curso já existe pelo nome
        for (Curso c : cursos) {
            if (c.getNome().equals(curso.getNome())) {
                // Se o curso já existe, não salvar e retornar false
                System.out.println("Curso já existe: " + curso.toString());
                return false;
            }
        }
        // Se o curso não existe, salvar e retornar true
        cursos.add(curso);
        System.out.println("Curso salvo: " + curso.toString());
        return true;
    }
    
    @Override
    public List<Curso> getCurso(){
        return cursos;
    }


}
