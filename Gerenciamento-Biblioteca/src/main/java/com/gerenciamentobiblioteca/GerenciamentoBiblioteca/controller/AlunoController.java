package com.gerenciamentobiblioteca.GerenciamentoBiblioteca.controller;

import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model.Aluno;
import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    @Autowired
    private final AlunoService alunoService;
    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> findall(){
        return  alunoService.findallAluno();
    }
     @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno salvarAluno(@RequestBody @Valid Aluno aluno) {
        return alunoService.salvarAluno(aluno);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Aluno updateAluno(@PathVariable Long id, @RequestBody @Valid Aluno alunoNovo){
        return alunoService.updateAluno(id, alunoNovo);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAluno(Long id){
        alunoService.deleteAluno(id);
    }
    

}
