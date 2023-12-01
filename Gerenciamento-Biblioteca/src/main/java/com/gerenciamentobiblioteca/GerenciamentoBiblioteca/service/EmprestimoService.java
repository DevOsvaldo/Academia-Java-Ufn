package com.gerenciamentobiblioteca.GerenciamentoBiblioteca.service;

import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.repository.AlunoRepository;
import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.repository.EmprestimoRepository;
import com.gerenciamentobiblioteca.GerenciamentoBiblioteca.repository.LivroRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    private final LivroRepository livroRepository;

    private final AlunoRepository alunoRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroRepository livroRepository
            , AlunoRepository alunoRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.alunoRepository = alunoRepository;
    }

    public List<Emprestimo> buscarEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo realizarEmprestimo(Long alunoId, Long livroId) {
        Livro livro = livroRepository.findById(livroId).orElseThrow(() -> new RuntimeException("Problema na busca, livro id: "
                + livroId + "não encontrado!"));
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Problema na busca, aluno id: "
                + alunoId + "não encontrado!"));

        if (livro.getStatusLivro() == SituacaoLivro.DISPONIVEL) {
            livro.setStatusLivro(SituacaoLivro.EMPRESTADO);
            livroRepository.save(livro);}

            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setAluno(aluno);
            emprestimo.setLivro(livro);
            LocalDate dataEmprestimo = LocalDate.now();
            emprestimo.setDataEmprestimo(dataEmprestimo);
            LocalDate dataDevolucao = dataEmprestimo.plusDays(7);
            emprestimo.setDataDevolucao(dataDevolucao);
            emprestimo.setSituacaoEmprestimo(SituacaoEmprestimo.ATIVO);

            return emprestimoRepository.save(emprestimo);

    }
    
    public Emprestimo devolverLivro(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId).orElseThrow(() -> new RuntimeException("Emprestimo id:"
                + emprestimoId + " não encontrado!"));

        if (emprestimo.getDataDevolucao() != null) {
            throw new RuntimeException("Livro devolvido!");
        }
        emprestimo.setSituacaoEmprestimo(SituacaoEmprestimo.DEVOLVIDO);
        emprestimo.setDataDevolucao(LocalDate.now());

        return emprestimoRepository.save(emprestimo);
    }
    public void deleteEmp(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId).
                orElseThrow(()-> new RuntimeException("Emprestimo não encontrado ou não existe"));
                emprestimoRepository.delete(emprestimo);

    }


    private boolean livroEstaEmprestado(Livro livro) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByLivroAndDataDevolucaoIsNull(livro);
        return !emprestimos.isEmpty();
    }
    /*
    * private boolean livroEstaEmprestado(Livro livro) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByLivroAndDataDevolucaoIsNull(livro);
        return !emprestimos.isEmpty();
    }*/

            /*
            //Verifica se há multa
            LocalDate dataDevolucao = emprestimo.getDataDevolucao();
            LocalDate dataDevolucaoEstipulada = LocalDate.now().plusDays(7); // Exemplo: devolução em até 7 dias

            if (dataDevolucao.isAfter(dataDevolucaoEstipulada)) {
                long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoEstipulada, dataDevolucao);
                double multa = diasAtraso * 5.0; // Multa de R$5.00 por dia de atraso
                livro.setStatusLivro(SituacaoLivro.ATRASADO);
                emprestimo.setMulta(multa); // Adiciona a multa ao objeto empréstimo
                System.out.println("A multa foi de: " + multa);
            }

            emprestimoRepository.save(emprestimo);
        }*/
    }




