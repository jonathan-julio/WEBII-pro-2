package com.jonathan.springmvcapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonathan.springmvcapp.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Integer>{


}
