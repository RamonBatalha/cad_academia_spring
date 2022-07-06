package com.academia.rb.cadastro_academia.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.academia.rb.cadastro_academia.model.Aluno;
import com.academia.rb.cadastro_academia.view.Controller.exception.ResourceNotFoundException;

@Repository
public class AlunoRepository_old {
    
    private List<Aluno> alunos = new ArrayList<Aluno>();
    private Integer ultimoId = 0;

         
    /**
     * Método para retornar lista de alunos
     * @return lista de todos os alunos
     */
    public List<Aluno> obterTodos() {
                return alunos;
            }

    /**
     * Método para retornar aluno de acordo com o id
     * @param id do aluno
     * @return aluno específico
     */
    public Optional<Aluno> obterPorId(Integer id){
        return alunos.stream().filter(aluno -> aluno.getId() == id).findFirst();
    }
    
    /**
     * Método para adicionar aluno a lista
     * @param aluno a ser adicionado
     * @return aluno
     */
    public Aluno adicionar(Aluno aluno) {
        ultimoId++;

        aluno.setId(ultimoId);
        alunos.add(aluno);

        return aluno;
    }
    
    /**
     * Método para remover aluno da lista
     * @param id do aluno
     */
    public void deletar (Integer id) {
        alunos.removeIf(aluno -> aluno.getId() == id);
    }
    
    /**
     * Método para atualizar informações do aluno
     * @param aluno informações atualizadas
     * @return aluno atualizado
     */
    public Aluno atualizar(Aluno aluno){

        Optional<Aluno> alunoEncontrado = obterPorId(aluno.getId());

        if(alunoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Aluno não encontrado");
        }

        deletar(aluno.getId());

        alunos.add(aluno);

        return aluno;
    }

}
