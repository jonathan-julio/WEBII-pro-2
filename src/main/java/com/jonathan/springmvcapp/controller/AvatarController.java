package com.jonathan.springmvcapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jonathan.springmvcapp.model.AlunoOut;
import com.jonathan.springmvcapp.model.Avatar;
import com.jonathan.springmvcapp.service.Aluno.AlunoService;
import com.jonathan.springmvcapp.service.Avatar.AvatarService;
import java.util.ArrayList;



@Controller
@RequestMapping("/avatar")
public class AvatarController {

    @Autowired
    @Qualifier("alunoServiceImpl")
    AlunoService alunoService;
    
    @Autowired
    AvatarService avatarService;
    
    @RequestMapping("/criarAvatar")
    public String getEstudantesPorSO(Model model) {
        List<AlunoOut> alunos = alunoService.getListaAluno();
        model.addAttribute("alunos", alunos);
        model.addAttribute("avatar", new Avatar());
        return "avatar/formAvatar";
    }

    @RequestMapping("/addAvatar")
    public String salvarAvatar(@ModelAttribute("avatar") Avatar avatar, Model model) {
        final boolean response =  avatarService.salvarAvatar(avatar);
        if (response) {
            return "avatar/cadastrorealizado";
        } else {
            return "avatar/erroCadastro";
        }
    }

    @RequestMapping("/listaAvatar")
    public String listarAvatar( Model model) {
        List<AlunoOut> alunos = alunoService.getListaAluno();
        List<AlunoOut> alunoOuts = new ArrayList<>();
        for (AlunoOut alunoOut : alunos) {
            if (alunoOut.getAvatar().getNomeFantasia() != "") {
                alunoOuts.add(alunoOut);
            }
        }
        model.addAttribute("alunos", alunoOuts);
        return "avatar/listaAvatar";
    }

}
