package com.jonathan.springmvcapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jonathan.springmvcapp.model.Aluno;
import com.jonathan.springmvcapp.model.AlunoOut;
import com.jonathan.springmvcapp.model.Curso;
import com.jonathan.springmvcapp.model.Pessoa;
import com.jonathan.springmvcapp.model.SistemaOperacional;
import com.jonathan.springmvcapp.repository.SORepostory;
import com.jonathan.springmvcapp.service.MockDataService;
import com.jonathan.springmvcapp.service.Aluno.AlunoService;
import com.jonathan.springmvcapp.service.Curso.CursoService;
import com.jonathan.springmvcapp.service.Curso.CursoServiceImpl;
import com.jonathan.springmvcapp.service.Pessoa.PessoaService;
import com.jonathan.springmvcapp.service.Pessoa.PessoaServiceImpl;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    @Qualifier("alunoServiceImpl")
    AlunoService alunoService;

    @Autowired
    PessoaService pessoaService = new PessoaServiceImpl();
    @Autowired
    CursoService cursoService =  new CursoServiceImpl();

    @Autowired
    SORepostory  soRepostory;




    @Autowired
    MockDataService mockDataService;

    public boolean init = false;

    @Autowired
    public void initAlunos() {
        List<Curso> curso = cursoService.getCurso() ;
        List<Pessoa> pessoa =  pessoaService.getPessoas();
       
        SistemaOperacional s1 = new SistemaOperacional("Windows");
        SistemaOperacional s2 = new SistemaOperacional("OSX");
        SistemaOperacional s3 = new SistemaOperacional("Linux");

        soRepostory.save(s1);
        soRepostory.save(s2);
        soRepostory.save(s3);

        List<SistemaOperacional> so =  soRepostory.findAll();



        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        List<Integer> l3 = new ArrayList<>();

        l1.add(so.get(0).getId());
        l1.add(so.get(2).getId());

        l2.add(1);
        l2.add(so.get(2).getId());

        l3.add(so.get(0).getId());
        l3.add(so.get(1).getId());
        l3.add(so.get(2).getId());

        


        Aluno aluno1 = new Aluno(pessoa.get(0).getId(), curso.get(0).getId(), "Java",  l1);
        Aluno aluno2 = new Aluno(pessoa.get(1).getId(), curso.get(0).getId(), "Python",l2);
        Aluno aluno3 = new Aluno(pessoa.get(2).getId(), curso.get(1).getId(), "C",l3);
        if (init == false) {
            alunoService.salvarAluno(aluno1);
            alunoService.salvarAluno(aluno2);
            alunoService.salvarAluno(aluno3);
            init = true;
        }
    }
    
    


    @RequestMapping("/showForm")
    public String showFormAluno(Model model) {
        model.addAttribute("aluno", new AlunoOut());
        model.addAttribute("cursos", cursoService.getCurso());
        model.addAttribute("SistemaOperacional", soRepostory.findAll() );
        return "aluno/formAluno";
    }

    @RequestMapping("/addAluno")
    public String showFormAluno(@ModelAttribute("aluno") AlunoOut aluno, Model model) {
        alunoService.salvarAlunoOut(aluno);
        model.addAttribute("aluno", aluno);
        return "aluno/cadastrorealizado";
    }

    @RequestMapping("/getListaAlunos")
    public String showListaAluno(Model model) {
        List<AlunoOut> alunos = alunoService.getListaAluno();
        model.addAttribute("alunos", alunos);
        return "aluno/listaAlunos";
    }

    @RequestMapping("/getListaAlunosCurso/curso={curso}")
    public String showListaAlunoCurso(@PathVariable("curso") String curso, Model model) {
        List<AlunoOut> alunos = alunoService.getListaAluno();
        List<AlunoOut> alunosPorCurso = new ArrayList<>();
        for (AlunoOut aluno : alunos) {
            if (aluno.getCurso().getNome().equals(curso)) {
                alunosPorCurso.add(aluno);
            }
        }
        model.addAttribute("alunos", alunosPorCurso);
        return "aluno/listaAlunosCurso";
    }

    @RequestMapping("/getListaAlunosLPF/lpf={lpf}")
    public String showListaAlunoLPF(@PathVariable("lpf") String linguagem, Model model) {
        List<AlunoOut> alunos = alunoService.getListaAluno();
        List<AlunoOut> alunosPorLinguagem = new ArrayList<>();
        for (AlunoOut aluno : alunos) {
            if (aluno.getLinguagem().equals(linguagem)) {
                alunosPorLinguagem.add(aluno);
            }
        }
        model.addAttribute("alunos", alunosPorLinguagem);
        return "aluno/listaAlunosLPF";
    }

    @RequestMapping("/getListaAlunosSO")
    public String showListaAlunoSO(@RequestParam("sistemasOperacionais") List<String> sistemasOperacionais,
            Model model) {
        List<AlunoOut> alunoOuts = alunoService.getListaAluno();
        Set<AlunoOut> alunosComSistemasOperacionaisComunsSet = new HashSet<>();
        List<AlunoOut> alunosComSistemasOperacionaisComuns = new ArrayList<>();

        for (AlunoOut aluno : alunoOuts) {
            List<SistemaOperacional> sistemasOperacionaisAluno = aluno.getSistemaOperacional(); 
            for (AlunoOut outroAluno : alunoOuts) {
                if (!aluno.equals(outroAluno)) {
                    List<SistemaOperacional> sistemasOperacionaisOutroAluno = outroAluno.getSistemaOperacional();
                    for (SistemaOperacional sistemaOperacional : sistemasOperacionaisAluno) {
                        if (sistemasOperacionaisOutroAluno.contains(sistemaOperacional)) {
                            alunosComSistemasOperacionaisComunsSet.add(aluno);
                            break;
                        }
                    }
                }
            }
        }

        alunosComSistemasOperacionaisComuns.addAll(alunosComSistemasOperacionaisComunsSet);
        model.addAttribute("alunos", alunosComSistemasOperacionaisComuns);
        return "aluno/listaAlunosSO";
    }


    @RequestMapping("/deteletarAluno/id={id}")
    public String deletarAluno(@PathVariable("id") Integer id, Model model) {
           AlunoOut aluno =   alunoService.deletarAluno(id);
            model.addAttribute("aluno", aluno);
            return "aluno/delete";
    }

    @RequestMapping("/estudantesPorCurso")
    public String getEstudantesPorCurso(Model model) {
        List<List<AlunoOut>>  aluno = alunoService.getEstudantesPorCurso(cursoService.getCurso());
            model.addAttribute("aluno", aluno);
            return "aluno/estudantesPorCurso";
    }

    @RequestMapping("/estudantesPorLPF")
    public String getEstudantesPorLPF(Model model) {
        List<List<AlunoOut>>  aluno = alunoService.getEstudantesPorLPF();
            model.addAttribute("aluno", aluno);
            return "aluno/estudantesPorLPF";
    }


}
