package com.jonathan.springmvcapp.service.Disciplina;

import java.util.List;

import org.springframework.stereotype.Service;
import com.jonathan.springmvcapp.model.Disciplina;

@Service
public interface DisciplinaService {
    public boolean salvarDisciplina(Disciplina curso);
    public List<Disciplina> getDisciplina();
    public Disciplina getDisciplinaById(Integer id);
    
}
