package com.jonathan.springmvcapp.service.Disciplina;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jonathan.springmvcapp.model.Avatar;
import com.jonathan.springmvcapp.model.Curso;
import com.jonathan.springmvcapp.model.Disciplina;
import com.jonathan.springmvcapp.repository.CursoRepository;
import com.jonathan.springmvcapp.repository.DisciplinaRepostory;

@Component
public class DisciplinaServiceImpl implements DisciplinaService {

   

    public boolean init = false;

    @Autowired
    CursoRepository cursoRepository;


    @Autowired
    DisciplinaRepostory disciplinaRepostory;
   
    @SuppressWarnings("null")
    @Override
    public boolean salvarDisciplina(Disciplina disciplina) {
        try {
            Disciplina disciplina2 = disciplinaRepostory.findByIdDisciplina(disciplina.getCodigo() );
            if (disciplina2 != null) {
                return false;
            }
            disciplinaRepostory.save(disciplina) ;
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    @Override
    public List<Disciplina> getDisciplina(){
        List<Disciplina> disciplinas = disciplinaRepostory.findAll();
        return disciplinas;
    }

    @Override
    public Disciplina getDisciplinaById(Integer id) {
        @SuppressWarnings("null")
        Disciplina disciplinas = disciplinaRepostory.findById(id).get();
        return disciplinas; // Ou outro valor padrão
    }







}
