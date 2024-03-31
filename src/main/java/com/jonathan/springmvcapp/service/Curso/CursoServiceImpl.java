package com.jonathan.springmvcapp.service.Curso;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jonathan.springmvcapp.model.Curso;
import com.jonathan.springmvcapp.repository.CursoRepository;

@Component
public class CursoServiceImpl implements CursoService {

   

    public boolean init = false;

    @Autowired
    CursoRepository cursoRepository;


    @Autowired
    public void initCurso() {
        Curso curso1 = new Curso("BTI", "primeiro");
        Curso curso2 = new Curso("EngSoft", "segundo");
        Curso curso3 = new Curso("CC", "nunca");

       
        if (init == false) {
            cursoRepository.save(curso1);
            cursoRepository.save(curso2);
            cursoRepository.save(curso3);
            init = true;
        }
    }
   
    @SuppressWarnings("null")
    @Override
    public boolean salvarCurso(Curso curso) {
        List<Curso> cursos = cursoRepository.findAll();
        // Verificar se o curso já existe pelo nome
        for (Curso c : cursos) {
            if (c.getNome().equals(curso.getNome())) {
                // Se o curso já existe, não salvar e retornar false
                System.out.println("Curso já existe: " + curso.toString());
                return false;
            }
        }
        // Se o curso não existe, salvar e retornar true
        cursoRepository.save(curso);
        System.out.println("Curso salvo: " + curso.toString());
        return true;
    }
    
    @Override
    public List<Curso> getCurso(){
        List<Curso> cursos = cursoRepository.findAll();
        return cursos;
    }

    @Override
    public Curso getCursoById(Integer id) {
        @SuppressWarnings("null")
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        return optionalCurso.orElse(null); // Ou outro valor padrão
    }


    @Override
    public boolean updateCurso(Curso curso) {

        List<Curso> cursos = cursoRepository.findAll();
        

        // Verificar se o curso já existe pelo nome
        for (Curso c : cursos) {
            if (c.getId() != curso.getId() && c.getNome().equals(curso.getNome()) ) {
                // Se o curso já existe, não salvar e retornar false
                System.out.println("Curso já existe: s " + curso.toString());
                return false;
            }
        }
        // Se o curso não existe, salvar e retornar true
        Integer id = curso.getId();
        String nome = curso.getNome();
        String desc = curso.getDescricao();
        List<String> disciplinas = curso.getDisciplinas();
        cursoRepository.updateCurso(id,nome,desc,disciplinas);
        return true;
    }
    @SuppressWarnings("null")
    @Override
    public boolean deleteCurso(Integer id){
        try {
            cursoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
       

    }





}
