package com.jonathan.springmvcapp.service.Aluno;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jonathan.springmvcapp.model.Aluno;
import com.jonathan.springmvcapp.model.AlunoOut;
import com.jonathan.springmvcapp.model.Curso;
import com.jonathan.springmvcapp.model.SistemaOperacional;

@Service
public interface AlunoService {

    public void salvarAluno(Aluno aluno);

    public void salvarAlunoOut(AlunoOut aluno);

    public AlunoOut deletarAluno(Integer id);

    public Aluno getAlunoById(Integer id);

    public List<AlunoOut> getListaAluno();

    public List<Aluno> getAlunosComSistemasOperacionaisComuns();

    public List<Aluno> getAlunosComSistemaOperacional(List<String> sistemasOperacionais);

    public List<List<AlunoOut>> getEstudantesPorCurso(List<Curso> list);

    public List<List<AlunoOut>> getEstudantesPorLPF();

    public List<Integer> getListSoIds(List<SistemaOperacional> list);

    public AlunoOut converAlunoOut(Aluno aluno);
}
