package com.gerenciamentobiblioteca.GerenciamentoBiblioteca.repository;

import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface LivroRepository extends JpaRepository<Livro, Long> {

  List<Livro> findByTituloContaining(String titulo);
  List<Livro> findByAutorContaining(String autor);

}
