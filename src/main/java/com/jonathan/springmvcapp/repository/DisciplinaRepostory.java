package com.jonathan.springmvcapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jonathan.springmvcapp.model.Disciplina;


@Repository
public interface DisciplinaRepostory extends JpaRepository<Disciplina,Integer> {
    
    @Query(value = "SELECT * FROM `disciplina` where codigo = :codigo",nativeQuery = true)
    Disciplina findByIdDisciplina(@Param("codigo") String codigo);

}
