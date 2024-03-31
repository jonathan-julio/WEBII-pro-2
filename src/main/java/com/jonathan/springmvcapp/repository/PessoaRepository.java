package com.jonathan.springmvcapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonathan.springmvcapp.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Integer>{


}
