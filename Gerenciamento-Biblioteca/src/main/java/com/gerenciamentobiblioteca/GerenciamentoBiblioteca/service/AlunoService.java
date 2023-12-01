package com.gerenciamentobiblioteca.GerenciamentoBiblioteca.service;

import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.repository.AlunoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> findallAluno() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> getAlunoById(Long id) {
        return alunoRepository.findById(id);
    }
    /*
    public Optional<Aluno> getAlunoByNome(String nome){
        return alunoRepository.findAllByNomeContaining(nome);
    }*/

    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);

    }

    public Aluno updateAluno(Long id, Aluno alunoNovo) {
        if (alunoRepository.existsById(id)) {
            alunoNovo.setId(id);
            return alunoRepository.save(alunoNovo);
        } else {
            throw new RuntimeException("Aluno não encontrado, ID:" + id + " não existe ");
        }
    }

    public void deleteAluno(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);

        }else {
            throw new RuntimeException("Aluno não encontrado, ID:" + id + " não existe ");
        }

    }
}