package com.academia.rb.cadastro_academia.view.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.rb.cadastro_academia.service.AlunoService;
import com.academia.rb.cadastro_academia.shared.AlunoDTO;
import com.academia.rb.cadastro_academia.view.model.AlunoRequest;
import com.academia.rb.cadastro_academia.view.model.AlunoResponse;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoService alunoService;
     
    @GetMapping
    public ResponseEntity<List<AlunoResponse>> obterTodos() {
        List<AlunoDTO> alunos = alunoService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<AlunoResponse> resposta = alunos.stream().map(alunoDto -> mapper.map(alunoDto, AlunoResponse.class)).collect(Collectors.toList()); 

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<AlunoResponse> adicionar(@RequestBody AlunoRequest alunoReq) {

        ModelMapper mapper = new ModelMapper();

        AlunoDTO alunoDto = mapper.map(alunoReq, AlunoDTO.class);

        alunoDto = alunoService.adicionar(alunoDto);

        return new ResponseEntity<>(mapper.map(alunoDto, AlunoResponse.class), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<AlunoResponse>> obterPorId(@PathVariable Integer id) {
    
        Optional<AlunoDTO> dto = alunoService.obterPorId(id);

        AlunoResponse aluno = new ModelMapper().map(dto.get(), AlunoResponse.class);

        return new ResponseEntity<>(Optional.of(aluno), HttpStatus.OK);
    } 
       
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        alunoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
     
     @PutMapping("/{id}")
     public ResponseEntity<AlunoResponse> atualizar(@PathVariable Integer id, @RequestBody AlunoRequest alunoReq) {
       
        ModelMapper mapper = new ModelMapper();

        AlunoDTO alunoDto = mapper.map(alunoReq, AlunoDTO.class);


        alunoDto = alunoService.atualizar(id, alunoDto);

        return new ResponseEntity<>(mapper.map(alunoDto, AlunoResponse.class), HttpStatus.OK);
     }
}
