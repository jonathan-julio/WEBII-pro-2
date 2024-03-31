package com.jonathan.springmvcapp.service.Aluno;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jonathan.springmvcapp.model.Aluno;
import com.jonathan.springmvcapp.model.AlunoOut;
import com.jonathan.springmvcapp.model.Avatar;
import com.jonathan.springmvcapp.model.Curso;
import com.jonathan.springmvcapp.model.Disciplina;
import com.jonathan.springmvcapp.model.SistemaOperacional;
import com.jonathan.springmvcapp.repository.AlunoRepository;
import com.jonathan.springmvcapp.repository.AvatarRepostory;
import com.jonathan.springmvcapp.repository.DisciplinaRepostory;
import com.jonathan.springmvcapp.repository.PessoaRepository;
import com.jonathan.springmvcapp.repository.SORepostory;
import com.jonathan.springmvcapp.service.Curso.CursoService;

@Component
public class AlunoServiceImpl implements AlunoService {


    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    CursoService cursoService;

    @Autowired
    AvatarRepostory avatarRepostory;

    @Autowired
    DisciplinaRepostory disciplinaRepostory;
    
    @Autowired
    SORepostory soRepostory;

    @Autowired
    PessoaRepository pessoaRepository;


  


    @Override
    public void salvarAluno(Aluno aluno) {
        System.out.println(aluno.toString());
        try {
            
            alunoRepository.save(aluno);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

    }

    @SuppressWarnings("null")
    @Override
    public void salvarAlunoOut(AlunoOut aluno) {
        System.out.println(aluno.toString());
        try {
            System.out.println("ALUNOOO" + aluno.toString());

            Integer idPessoa =  pessoaRepository.saveAndFlush(aluno.getPessoa()).getId();
            Aluno aluno1 = new Aluno(idPessoa , aluno.getCurso().getId(), aluno.getLinguagem() ,  getListSoIds(aluno.getSistemaOperacional() )  );
            alunoRepository.save(aluno1);
            System.out.println("Pessoa  " + pessoaRepository.findById(idPessoa));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

    }

    @SuppressWarnings("null")
    @Override
    public AlunoOut deletarAluno(Integer id) {
        AlunoOut alunoParaRetornar = converAlunoOut(alunoRepository.findById(id).get()) ;
        alunoRepository.deleteById(id);

        return alunoParaRetornar;
    }

    @Override
    public Aluno getAlunoById(Integer id) {
        List<Aluno> alunos = alunoRepository.findAll();
        for (Aluno aluno : alunos) {
            if (aluno.getId().equals(id)) {
                return aluno;
            }
        }
        return null;
    }


    @SuppressWarnings({ "deprecation", "null" })
    @Override
    public AlunoOut converAlunoOut(Aluno aluno){
        AlunoOut alunoOut = new AlunoOut();

        Avatar avatarAut = new Avatar(0, 0, "");
        Avatar avatar = avatarRepostory.findByIdAluno(aluno.getId());
        if (avatar != null) {
            avatarAut = avatar;
        }
        Curso curso = cursoService.getCursoById(aluno.getCurso());
        alunoOut.setId(aluno.getId());
        alunoOut.setPessoa(pessoaRepository.getById(aluno.getPessoa()));
        alunoOut.setCurso(curso);
        alunoOut.setLinguagem(aluno.getLinguagem());
        alunoOut.setSistemaOperacional(getListSoNames(aluno.getSistemaOperacional()));
        alunoOut.setAvatar(avatarAut);
        
        return alunoOut;


    }

    @Override
    public List<List<AlunoOut>> getEstudantesPorCurso(List<Curso> cursos) {
        List<Aluno> alunos = alunoRepository.findAll();
        List<List<AlunoOut>> listaporcurso = new ArrayList<>();
        for (Curso curso : cursos) {
            List<AlunoOut> alunosPorCurso = new ArrayList<>();
            for (Aluno aluno : alunos) {
                if (aluno.getCurso().equals(curso.getId())) {
                    alunosPorCurso.add(converAlunoOut(aluno));
                }
            }
            if (!alunosPorCurso.isEmpty()) {
                listaporcurso.add(alunosPorCurso);
            }
        }
        return listaporcurso;
    }

    

    @Override
    public List<Aluno> getAlunosComSistemasOperacionaisComuns() {
        List<Aluno> alunos = alunoRepository.findAll();
        Set<Aluno> alunosComSistemasOperacionaisComunsSet = new HashSet<>();
        List<Aluno> alunosComSistemasOperacionaisComuns = new ArrayList<>();

        for (Aluno aluno : alunos) {
            List<Integer> sistemasOperacionaisAluno = aluno.getSistemaOperacional(); 
            for (Aluno outroAluno : alunos) {
                if (!aluno.equals(outroAluno)) {
                    List<Integer> sistemasOperacionaisOutroAluno = outroAluno.getSistemaOperacional();
                    for (Integer sistemaOperacional : sistemasOperacionaisAluno) {
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
        List<Aluno> alunos = alunoRepository.findAll();

        for (Aluno aluno : alunos) {
            List<Integer> sistemasOperacionaisAluno = aluno.getSistemaOperacional();
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

    public List<Integer> getListSoIds(List<SistemaOperacional> list){
        List<Integer> sistemaOperacional = new ArrayList<>();
        for (SistemaOperacional  so : list ) {
            sistemaOperacional.add(so.getId() );
        }
        return sistemaOperacional;
    }

    @SuppressWarnings({ "deprecation", "null" })
    public List<SistemaOperacional> getListSoNames(List<Integer> list){
        List<SistemaOperacional> sistemaOperacional = new ArrayList<>();
        for (Integer  so : list ) {
            sistemaOperacional.add(soRepostory.getById(so) );
        }
        return sistemaOperacional;
    }


    @SuppressWarnings("deprecation")
    @Override
    public List<AlunoOut> getListaAluno() {
        List<AlunoOut> alunosReturn = new ArrayList<>();
        List<Aluno> alunos = alunoRepository.findAll();

        for (Aluno aluno : alunos) {
        
            List<SistemaOperacional> sistemaOperacional = new ArrayList<>();
    
            if (aluno.getSistemaOperacional() != null) {
                for (Integer so : aluno.getSistemaOperacional()) {
                    sistemaOperacional.add(soRepostory.getById(so));
                }
            }

            AlunoOut aluno3 =  converAlunoOut(aluno);

            alunosReturn.add(aluno3);
        }

        System.out.println("FIM");
        return alunosReturn;
    }

    @Override
    public List<List<AlunoOut>> getEstudantesPorLPF() {
        List<Aluno> alunos = alunoRepository.findAll();
        List<List<AlunoOut>> listaporlpf = new ArrayList<>();
        List<String> LPF = new ArrayList<>();
        for (Aluno aluno : alunos) {
            LPF.add(aluno.getLinguagem());
        }
        Set<String> set = new HashSet<>(LPF);
        LPF.clear();
        LPF.addAll(set);

        for (String lpf : LPF) {
            List<AlunoOut> alunosPorLPF = new ArrayList<>();
            for (Aluno aluno : alunos) {
                if (aluno.getLinguagem().equals(lpf)) {
                    alunosPorLPF.add(converAlunoOut(aluno));
                }
            }
            if (!alunosPorLPF.isEmpty()) {
                listaporlpf.add(alunosPorLPF);
            }
        }
        return listaporlpf;
    }


    

    /* @Override
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
    } */

}
