package com.jonathan.springmvcapp.service.Curso;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jonathan.springmvcapp.model.Curso;

@Service
public interface CursoService {
    public boolean salvarCurso(Curso curso);
    public List<Curso> getCurso();
    public Curso getCursoById(Integer id);
    public boolean updateCurso(Curso curso);
    public boolean deleteCurso(Integer id);

    
}
