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

@Entity
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String curso;

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private Set<Emprestimo> emprestimos;


}
