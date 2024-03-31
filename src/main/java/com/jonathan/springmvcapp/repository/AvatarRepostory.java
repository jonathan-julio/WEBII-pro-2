package com.jonathan.springmvcapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jonathan.springmvcapp.model.Avatar;


@Repository
public interface AvatarRepostory extends JpaRepository<Avatar,Integer>{


    @Query(value = "SELECT * FROM `avatar` where id_aluno = :id",nativeQuery = true)
    Avatar findByIdAluno(@Param("id") Integer id);

    
}
