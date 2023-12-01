package com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "tb_livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private int anoDePublicacao;

    @OneToMany(mappedBy = "livro")
    @JsonIgnore
    private Set<Emprestimo> emprestimos;

    @Enumerated(EnumType.STRING)
    private SituacaoLivro statusLivro;

}
