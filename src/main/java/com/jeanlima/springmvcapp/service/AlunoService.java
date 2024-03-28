package com.jeanlima.springmvcapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanlima.springmvcapp.model.Aluno;
import com.jeanlima.springmvcapp.model.SoLista;

@Service
public interface AlunoService {

    public void salvarAluno(Aluno aluno);

    public Aluno deletarAluno(String id);

    public Aluno getAlunoById(String id);

    public List<Aluno> getListaAluno();

    public List<Aluno> getAlunosPorCurso(String curso);

    public List<Aluno> getAlunosPorLinguagem(String linguagem);

    public List<Aluno> getAlunosComSistemasOperacionaisComuns();

    public List<Aluno> getAlunosComSistemaOperacional(List<String> sistemasOperacionais);

    public List<List<Aluno>> getEstudantesPorCurso(String[] cursos);

    public List<List<Aluno>> getEstudantesPorLPF();

    public List<SoLista> getEstudantesPorSO(String[] SO);
}
