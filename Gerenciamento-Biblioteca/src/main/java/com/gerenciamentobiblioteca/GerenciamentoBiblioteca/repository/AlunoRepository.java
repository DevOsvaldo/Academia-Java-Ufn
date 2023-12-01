package com.gerenciamentobiblioteca.GerenciamentoBiblioteca.repository;

import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



public interface AlunoRepository extends JpaRepository<Aluno, Long> {

 //   Optional<Aluno> findAllByNomeContaining(String nome);

}
