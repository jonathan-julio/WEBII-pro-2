package com.jeanlima.springmvcapp.controller;

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

import com.jeanlima.springmvcapp.model.Aluno;
import com.jeanlima.springmvcapp.model.Curso;
import com.jeanlima.springmvcapp.model.SoLista;
import com.jeanlima.springmvcapp.service.AlunoService;
import com.jeanlima.springmvcapp.service.CursoService;
import com.jeanlima.springmvcapp.service.MockDataService;

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
        model.addAttribute("aluno", new Curso());
        model.addAttribute("curso", new Curso());
        model.addAttribute("cursos", mockDataService.getCursos());
        model.addAttribute("sistemasOperacionais", mockDataService.getSistemasOperacionais());
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


}
