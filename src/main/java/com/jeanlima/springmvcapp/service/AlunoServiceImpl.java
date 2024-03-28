package com.jeanlima.springmvcapp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanlima.springmvcapp.model.Aluno;
import com.jeanlima.springmvcapp.model.Curso;
import com.jeanlima.springmvcapp.model.SoLista;

@Component
public class AlunoServiceImpl implements AlunoService {

  

    public List<Aluno> alunos = new ArrayList<Aluno>();

    @Override
    public void salvarAluno(Aluno aluno) {
        System.out.println(aluno.toString());
        try {
            aluno.setId(UUID.randomUUID().toString());
            this.alunos.add(aluno);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

    }

    @Override
    public Aluno deletarAluno(String id) {
        Aluno alunoParaDeletar = null;
        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(id)) {
                alunoParaDeletar = aluno;
                break;
            }
        }
        if (alunoParaDeletar != null) {
            alunos.remove(alunoParaDeletar);
            return alunoParaDeletar;
        } else {
            return new Aluno();
             }
    }

    @Override
    public Aluno getAlunoById(String id) {
        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(id)) {
                return aluno;
            }
        }
        return null;
    }

    @Override
    public List<Aluno> getAlunosPorCurso(String curso) {
        List<Aluno> alunosPorCurso = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getCurso().equals(curso)) {
                alunosPorCurso.add(aluno);
            }
        }
        return alunosPorCurso;
    }

    @Override
    public List<List<Aluno>> getEstudantesPorCurso(String[] cursos) {
        List<List<Aluno>> listaporcurso = new ArrayList<>();
        for (String curso : cursos) {
            List<Aluno> alunosPorCurso = new ArrayList<>();
            for (Aluno aluno : alunos) {
                if (aluno.getCurso().equals(curso)) {
                    alunosPorCurso.add(aluno);
                }
            }
            if (!alunosPorCurso.isEmpty()) {
                listaporcurso.add(alunosPorCurso);
            }
        }
        return listaporcurso;
    }

    @Override
    public List<Aluno> getAlunosPorLinguagem(String linguagem) {
        List<Aluno> alunosPorLinguagem = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getLinguagem().equals(linguagem)) {
                alunosPorLinguagem.add(aluno);
            }
        }
        return alunosPorLinguagem;
    }

    @Override
    public List<Aluno> getAlunosComSistemasOperacionaisComuns() {
        Set<Aluno> alunosComSistemasOperacionaisComunsSet = new HashSet<>();
        List<Aluno> alunosComSistemasOperacionaisComuns = new ArrayList<>();

        for (Aluno aluno : alunos) {
            List<String> sistemasOperacionaisAluno = aluno.getSistemasOperacionais();
            for (Aluno outroAluno : alunos) {
                if (!aluno.equals(outroAluno)) {
                    List<String> sistemasOperacionaisOutroAluno = outroAluno.getSistemasOperacionais();
                    for (String sistemaOperacional : sistemasOperacionaisAluno) {
                        if (sistemasOperacionaisOutroAluno.contains(sistemaOperacional)) {
                            alunosComSistemasOperacionaisComunsSet.add(aluno);
                            break;
                        }
                    }
                }
            }
        }

        alunosComSistemasOperacionaisComuns.addAll(alunosComSistemasOperacionaisComunsSet);
        return alunosComSistemasOperacionaisComuns;
    }

    @Override
    public List<Aluno> getAlunosComSistemaOperacional(List<String> sistemasOperacionais) {
        List<Aluno> alunosComSistemaOperacionalComum = new ArrayList<>();

        for (Aluno aluno : alunos) {
            List<String> sistemasOperacionaisAluno = aluno.getSistemasOperacionais();
            boolean hasCommonOS = false;

            for (String so : sistemasOperacionais) {
                if (sistemasOperacionaisAluno.contains(so)) {
                    hasCommonOS = true;
                    break;
                }
            }

            if (hasCommonOS) {
                alunosComSistemaOperacionalComum.add(aluno);
            }
        }

        return alunosComSistemaOperacionalComum;
    }

    @Override
    public List<Aluno> getListaAluno() {
        return alunos;
    }

    @Override
    public List<List<Aluno>> getEstudantesPorLPF() {
        List<List<Aluno>> listaporlpf = new ArrayList<>();
        String[] LPF= {
            "Java", "C", "Python", "Javascript"
        };
        for (String lpf : LPF) {
            List<Aluno> alunosPorLPF = new ArrayList<>();
            for (Aluno aluno : alunos) {
                if (aluno.getLinguagem().equals(lpf)) {
                    alunosPorLPF.add(aluno);
                }
            }
            if (!alunosPorLPF.isEmpty()) {
                listaporlpf.add(alunosPorLPF);
            }
        }
        return listaporlpf;
    }

    @Override
    public List<SoLista> getEstudantesPorSO(String[] SO) {
        List<SoLista> listaporSo = new ArrayList<>();
        for (String so : SO) {
            List<Aluno> alunosPorSO = new ArrayList<>();
            for (Aluno aluno : alunos) {
                if (aluno.getSistemasOperacionais().contains(so)) {
                    alunosPorSO.add(aluno);
                }
            }
            if (!alunosPorSO.isEmpty()) {
                SoLista soListaAux = new SoLista(so,alunosPorSO);
                listaporSo.add(soListaAux);
            }
        }
        System.out.println("listaporSo: " + listaporSo.get(0).getAluno().get(0).getPrimeiroNome() );
        return listaporSo;
    }

}
