package com.academia.rb.cadastro_academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.rb.cadastro_academia.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
    
}
