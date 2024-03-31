package com.jonathan.springmvcapp.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.jonathan.springmvcapp.model.Aluno;
import com.jonathan.springmvcapp.model.Curso;
import com.jonathan.springmvcapp.model.SistemaOperacional;
import com.jonathan.springmvcapp.service.MockDataService;
import com.jonathan.springmvcapp.service.Aluno.AlunoService;
import com.jonathan.springmvcapp.service.Curso.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    @Qualifier("cursoServiceImpl")
    CursoService cursoService;

    @Autowired
    MockDataService mockDataService;

    @RequestMapping("/showForm")
    public String showFormAluno(Model model) {
        model.addAttribute("curso", new Curso());
        return "curso/formCurso";
    }
    

    @RequestMapping("/listac-cursos")
    public String ListCursos(Model model) {
        List<Curso> cursos =  cursoService.getCurso();
        model.addAttribute("cursos", cursos);
        return "curso/listaCurso";
    }

    @RequestMapping("/editarCurso/id={id}")
    public String editarCurso(@ModelAttribute("id") Integer id,Model model) {
        Curso curso = cursoService.getCursoById(id);
        model.addAttribute("curso", curso);
        return "curso/editeFormCurso";
    }

    @RequestMapping("/deteletarCurso/id={id}")
    public String deleteCurso(@ModelAttribute("id") Integer id,Model model) {
        model.addAttribute("curso", new Curso());
        return "curso/formCurso";
    }
    


    @RequestMapping("/addCurso")
    public String showFormAluno(@ModelAttribute("curso") Curso curso, Model model) {

        final boolean response = cursoService.salvarCurso(curso);
        if (response) {
            return "curso/cadastrorealizado";
        } else {
            return "curso/erroCadastro";
        }
        
    }

    @RequestMapping("/editeCurso")
    public String editeShowForm(@ModelAttribute("curso") Curso curso, Model model) {

        final boolean response = cursoService.updateCurso(curso);
        if (response) {
            return "curso/cadastrorealizado";
        } else {
            return "curso/erroCadastro";
        }
        
    }

    /* @RequestMapping("/deteletarAluno/id={curso}")
    public String editarCurso(@ModelAttribute("curso") Curso curso,Model model) {
        model.addAttribute("aluno", new Curso());
        model.addAttribute("curso", new Curso());
        model.addAttribute("cursos", mockDataService.getCursos());
        model.addAttribute("sistemasOperacionais", mockDataService.getSistemasOperacionais());
        return "curso/formCurso";
    } */


}
