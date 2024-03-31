package com.jonathan.springmvcapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonathan.springmvcapp.model.SistemaOperacional;


@Repository
public interface SORepostory extends JpaRepository<SistemaOperacional,Integer>{
    
}
