package com.jonathan.springmvcapp.repository;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jonathan.springmvcapp.model.Curso;

import jakarta.transaction.Transactional;


@Repository
public interface CursoRepository extends JpaRepository<Curso ,Integer>{


    @Modifying
    @Transactional
    @Query("UPDATE Curso c SET c.nome = :nome, c.descricao = :descricao, c.disciplinas = :disciplinas WHERE c.id = :id")
    void updateCurso(@Param("id") int id, @Param("nome") String nome, @Param("descricao") String descricao,@Param("disciplinas") java.util.List<String> disciplinas);

    
}
