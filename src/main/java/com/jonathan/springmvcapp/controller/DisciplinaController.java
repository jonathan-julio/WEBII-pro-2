package com.jonathan.springmvcapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jonathan.springmvcapp.model.Curso;
import com.jonathan.springmvcapp.model.Disciplina;
import com.jonathan.springmvcapp.service.Aluno.AlunoService;
import com.jonathan.springmvcapp.service.Curso.CursoService;
import com.jonathan.springmvcapp.service.Disciplina.DisciplinaService;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    @Qualifier("alunoServiceImpl")
    AlunoService alunoService;

    @Autowired
    DisciplinaService disciplinaService;

    @Autowired
    CursoService cursoService;
  

    

    @RequestMapping("/criarDisciplina")
    public String getEstudantesPorSO(Model model) {
        List<Curso> cursos = cursoService.getCurso();
      

        model.addAttribute("cursos", cursos);
        model.addAttribute("disciplina", new Disciplina());

        return "disciplina/formDisciplina";
    }

    @PostMapping("/addDisciplina")
    public String adicionarDisciplina(@ModelAttribute Disciplina disciplina, @RequestParam List<Integer> cursoIds) {



        final boolean response = disciplinaService.salvarDisciplina(disciplina);
       
        System.out.println("*********************: " + response );
        if (response) {
            List<Curso> cursos = cursoService.getCurso();
            for (Integer ids : cursoIds) {
                for (Curso curso : cursos) {
                    if (ids.equals(curso.getId())) {
                        curso.setDisciplinas(List.of(disciplina.getCodigo()));
                        cursoService.updateCurso(curso);
                    }
                }
            }
            System.out.println("*********************: disciplina/cadastrorealizado" );
            return "disciplina/cadastrorealizado";
        }
        System.out.println("*********************: disciplina/erroCadastro" );
        return "disciplina/erroCadastro";
        
    }


    @RequestMapping("/listaDisciplina")
    public String listarDisciplina( Model model) {
        List<Disciplina> disciplinas = disciplinaService.getDisciplina();
        model.addAttribute("disciplinas", disciplinas);
        return "disciplina/listaDisciplina";
    }

}
