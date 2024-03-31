package com.jonathan.springmvcapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jonathan.springmvcapp.model.Curso;
import com.jonathan.springmvcapp.service.Curso.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    @Qualifier("cursoServiceImpl")
    CursoService cursoService;

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
        
        final boolean response = cursoService.deleteCurso(id);
        model.addAttribute("success", response);
        return "curso/delete";
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
        curso.setDisciplinas(cursoService.getCursoById(curso.getId()).getDisciplinas());
        final boolean response = cursoService.updateCurso(curso);
        if (response) {
            return "curso/cadastrorealizado";
        } else {
            return "curso/erroCadastro";
        }
        
    }


}
