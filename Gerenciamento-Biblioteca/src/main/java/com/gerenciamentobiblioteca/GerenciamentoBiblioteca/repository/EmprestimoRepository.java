package com.gerenciamentobiblioteca.GerenciamentoBiblioteca.repository;

import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model.Emprestimo;
import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;


public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
     List<Emprestimo> findByLivroAndDataDevolucaoIsNull(Livro livro);

}
