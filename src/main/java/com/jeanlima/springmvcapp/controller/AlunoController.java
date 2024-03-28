package com.jeanlima.springmvcapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeanlima.springmvcapp.model.Aluno;
import com.jeanlima.springmvcapp.model.Curso;
import com.jeanlima.springmvcapp.model.SoLista;
import com.jeanlima.springmvcapp.service.AlunoService;
import com.jeanlima.springmvcapp.service.CursoService;
import com.jeanlima.springmvcapp.service.CursoServiceImpl;
import com.jeanlima.springmvcapp.service.MockDataService;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    @Qualifier("alunoServiceImpl")
    AlunoService alunoService;

    @Autowired
    CursoService cursoService =  new CursoServiceImpl();

    @Autowired
    MockDataService mockDataService;

    
    

    public boolean init = false;

    @Autowired
    public void initAlunos() {
        List<Curso> curso = cursoService.getCurso() ;
        
        Aluno aluno1 = new Aluno(UUID.randomUUID().toString(),"João", "Silva", curso.get(0).getNome(), "Java", "joao@email.com",
                List.of("Windows", "Linux"));

        Aluno aluno2 = new Aluno(UUID.randomUUID().toString(),"Maria", "Santos", curso.get(0).getNome(), "Python", "maria@email.com",
                List.of("OSX", "Linux"));

        Aluno aluno3 = new Aluno(UUID.randomUUID().toString(),"Pedro", "Oliveira", curso.get(1).getNome(), "C", "pedro@email.com",
                List.of("Windows", "Linux", "OSX"));
        if (init == false) {
            alunoService.salvarAluno(aluno1);
            alunoService.salvarAluno(aluno2);
            alunoService.salvarAluno(aluno3);
            init = true;
        }
    }

    @RequestMapping("/showForm")
    public String showFormAluno(Model model) {

        model.addAttribute("aluno", new Aluno());
        model.addAttribute("cursos", cursoService.getCurso());
        model.addAttribute("sistemasOperacionais", mockDataService.getSistemasOperacionais());
        return "aluno/formAluno";
    }

    @RequestMapping("/addAluno")
    public String showFormAluno(@ModelAttribute("aluno") Aluno aluno, Model model) {
        alunoService.salvarAluno(aluno);
        model.addAttribute("aluno", aluno);

        return "aluno/paginaAluno";
    }

    @RequestMapping("/getListaAlunos")
    public String showListaAluno(Model model) {
        List<Aluno> alunos = alunoService.getListaAluno();
        model.addAttribute("alunos", alunos);
        return "aluno/listaAlunos";
    }

    @RequestMapping("/getListaAlunosCurso/curso={curso}")
    public String showListaAlunoCurso(@PathVariable("curso") String curso, Model model) {
        List<Aluno> alunos = alunoService.getAlunosPorCurso(curso);
        model.addAttribute("alunos", alunos);
        return "aluno/listaAlunosCurso";
    }

    @RequestMapping("/getListaAlunosLPF/lpf={lpf}")
    public String showListaAlunoLPF(@PathVariable("lpf") String lpf, Model model) {
        System.err.println("CURSOOO: " + lpf);
        List<Aluno> alunos = alunoService.getAlunosPorLinguagem(lpf);
        model.addAttribute("alunos", alunos);
        return "aluno/listaAlunosLPF";
    }

    @RequestMapping("/getListaAlunosSO")
    public String showListaAlunoSO(@RequestParam("sistemasOperacionais") List<String> sistemasOperacionais,
            Model model) {
        System.err.println("CURSOOO: " + sistemasOperacionais);
        List<Aluno> alunos = alunoService.getAlunosComSistemaOperacional(sistemasOperacionais);
        model.addAttribute("alunos", alunos);
        return "aluno/listaAlunosSO";
    }

    // Request para retornar a lista de estudantes que têm ao menos um sistema
    // operacional favorito em comum
    @RequestMapping("/alunosComSistemasOperacionaisComuns")
    public String alunosComSistemasOperacionaisComuns(Model model) {
        List<Aluno> alunosComSistemasOperacionaisComuns = alunoService.getAlunosComSistemasOperacionaisComuns();
        model.addAttribute("alunos", alunosComSistemasOperacionaisComuns);
        return "aluno/listaAlunos";
    }


    @RequestMapping("/detelheAluno/id={id}")
    public String getAlunoById(@PathVariable("id") String id, Model model) {
        Aluno aluno = alunoService.getAlunoById(id);
        System.err.println("ALUNOD: " + aluno.toString());
        model.addAttribute("aluno", aluno);
        return "aluno/detalhe";
    }

    @RequestMapping("/deteletarAluno/id={id}")
    public String deletarAluno(@PathVariable("id") String id, Model model) {
            Aluno aluno = alunoService.deletarAluno(id);
            model.addAttribute("aluno", aluno);
            return "aluno/delete";
    }

    @RequestMapping("/estudantesPorCurso")
    public String getEstudantesPorCurso(Model model) {
        List<List<Aluno>>  aluno = alunoService.getEstudantesPorCurso(mockDataService.getCursos());
            model.addAttribute("aluno", aluno);
            return "aluno/estudantesPorCurso";
    }

    @RequestMapping("/estudantesPorLPF")
    public String getEstudantesPorLPF(Model model) {
        List<List<Aluno>>  aluno = alunoService.getEstudantesPorLPF();
            model.addAttribute("aluno", aluno);
            return "aluno/estudantesPorLPF";
    }

    @RequestMapping("/estudantesPorSO")
    public String getEstudantesPorSO(Model model) {
        List<SoLista>  ListaSO = alunoService.getEstudantesPorSO(mockDataService.getSistemasOperacionais());

        model.addAttribute("ListaSO", ListaSO);
        return "aluno/estudantesPorSO";
    }

}
