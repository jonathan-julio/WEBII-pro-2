package com.jonathan.springmvcapp.service.Pessoa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jonathan.springmvcapp.model.Pessoa;
import com.jonathan.springmvcapp.repository.PessoaRepository;




@Component
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public boolean init = false;

    @Autowired
    public void initCurso() {
        Pessoa p1 = new Pessoa("João", "Silva","joao@email.com");
        Pessoa p2 = new Pessoa("Maria", "Santos","maria@email.com");
        Pessoa p3 = new Pessoa("pedro", "Oliveira","pedro@email.com");
       
        if (init == false) {
            pessoaRepository.save(p1);
            pessoaRepository.save(p2);
            pessoaRepository.save(p3);
            init = true;
        }
    }

    @Override
    public List<Pessoa> getPessoas(){
        return pessoaRepository.findAll();
    }


    
}
