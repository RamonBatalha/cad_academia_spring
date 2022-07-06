package com.academia.rb.cadastro_academia.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.academia.rb.cadastro_academia.model.Aluno;
import com.academia.rb.cadastro_academia.repository.AlunoRepository;
import com.academia.rb.cadastro_academia.shared.AlunoDTO;
import com.academia.rb.cadastro_academia.view.Controller.exception.ResourceNotFoundException;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    /**
     * Método para retornar lista de alunos
     * 
     * @return lista de todos os alunos
     */
    public List<AlunoDTO> obterTodos() {

        List<Aluno> alunos = alunoRepository.findAll();

        return alunos.stream().map(aluno -> new ModelMapper().map(aluno, AlunoDTO.class)).collect(Collectors.toList());
    }

    /**
     * Método para retornar aluno de acordo com o id
     * 
     * @param id do aluno
     * @return aluno específico
     */
    public Optional<AlunoDTO> obterPorId(Integer id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);

        if (aluno.isEmpty()) {
            throw new ResourceNotFoundException("Produto com id " + id + " não encontrado");
        }

        AlunoDTO dto = new ModelMapper().map(aluno.get(), AlunoDTO.class);
        return Optional.of(dto);
    }

    /**
     * Método para adicionar aluno a lista
     * 
     * @param aluno a ser adicionado
     * @return aluno
     */
    public AlunoDTO adicionar(AlunoDTO alunoDto) {
        
        alunoDto.setId(null);

        ModelMapper mapper = new ModelMapper();

        Aluno aluno = mapper.map(alunoDto, Aluno.class);

        aluno = alunoRepository.save(aluno);

        alunoDto.setId(aluno.getId());

        return alunoDto;
    }

    /**
     * Método para remover aluno da lista
     * 
     * @param id do aluno
     */
    public void deletar(Integer id) {
       Optional<Aluno> aluno =  alunoRepository.findById(id);

       if (aluno.isEmpty()) {
        throw new ResourceNotFoundException("não foi possível encontrar o aluno com o id " + id);
       }

       alunoRepository.deleteById(id);
    }

    /**
     * Método para atualizar informações do aluno
     * 
     * @param aluno informações atualizadas
     * @return aluno atualizado
     */
    public AlunoDTO atualizar(Integer id, AlunoDTO alunoDto) {
       
        Optional<Aluno> alunoId =  alunoRepository.findById(id);

        if (alunoId.isEmpty()) {
         throw new ResourceNotFoundException("não foi possível encontrar o aluno com o id " + id);
        }

       alunoDto.setId(id);

       ModelMapper mapper = new ModelMapper();

       Aluno aluno = mapper.map(alunoDto, Aluno.class);

       alunoRepository.save(aluno);

       return alunoDto;
    }
}
