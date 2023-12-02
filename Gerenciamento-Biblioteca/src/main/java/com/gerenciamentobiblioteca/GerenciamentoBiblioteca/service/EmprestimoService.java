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
        Livro livro = livroRepository.findById(livroId).orElseThrow(() ->
                new RuntimeException("Problema na busca, livro id: "
                + livroId + "não encontrado!"));
        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() ->
                new RuntimeException("Problema na busca, aluno id: "
                + alunoId + "não encontrado!"));

        if (livro.getStatusLivro() == SituacaoLivro.DISPONIVEL) {
            livro.setStatusLivro(SituacaoLivro.EMPRESTADO);
            livroRepository.save(livro);
        }else {
            throw  new RuntimeException("Livro já está emprestado, tente outro!!");
        }


            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setAluno(aluno);
            emprestimo.setLivro(livro);
            LocalDate dataEmprestimo = LocalDate.now();
            emprestimo.setDataEmprestimo(dataEmprestimo);
            LocalDate dataDevolucaoEsperada = dataEmprestimo.plusDays(7);
            emprestimo.setDataDevolucaoEsperada(dataDevolucaoEsperada);
            emprestimo.setSituacaoEmprestimo(SituacaoEmprestimo.ATIVO);

            return emprestimoRepository.save(emprestimo);

    }
    public Emprestimo devolverLivro(Long emprestimoId, LocalDate dataDevolucao) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Emprestimo id:" + emprestimoId + " não encontrado!"));
        emprestimo.setDataDevolucao(dataDevolucao);
        if (emprestimo.getDataDevolucao() == null) {
            System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());
            throw new RuntimeException("Devolução!!");
        }

        LocalDate dataDevolucaoEsperada = emprestimo.getDataDevolucaoEsperada();

        if (dataDevolucaoEsperada == null) {
            throw new RuntimeException("Data de devolução esperada não definida!");
        }

        long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoEsperada, dataDevolucao);

        // Verifica se há atraso e calcula a multa
        if (diasAtraso > 0) {
            double valorMultaPorDia = 1.00;
            double multa = diasAtraso * valorMultaPorDia;

            // Certifica-se de que a multa seja pelo menos 0
            emprestimo.setMulta(Math.max(0, multa));
        } else {
            emprestimo.setMulta(0); // Sem multa se devolvido antes da data de devolução esperada
        }

        emprestimo.setSituacaoEmprestimo(SituacaoEmprestimo.DEVOLVIDO);

        // Atualiza o status do livro para DISPONÍVEL
        Livro livroDevolvido = emprestimo.getLivro();
        livroDevolvido.atualizarStatusParaDisponivel();

        return emprestimoRepository.save(emprestimo);
    }
   /* public Emprestimo devolverLivro(Long emprestimoId, LocalDate dataDevolucao) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new RuntimeException("Emprestimo id:" + emprestimoId + " não encontrado!"));

        if (emprestimo.getDataDevolucao() == null) {
            System.out.println("Data de devolução: "+emprestimo.getDataDevolucao());
            throw new RuntimeException("Devolução!!");
        }
        LocalDate dataDevolucaoEsperada = emprestimo.getDataDevolucaoEsperada();

        if (dataDevolucaoEsperada == null) {
            throw new RuntimeException("Data de devolução esperada não definida!");
        }

        //Calcula a diferença entre a data de devolução passada no ato do emprestimo e a data de devolução real
        long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoEsperada(), emprestimo.getDataDevolucao());
        // Define o valor da multa (por exemplo, R$ 1,00 por dia de atraso)
        if (diasAtraso > 0) {
            double valorMultaPorDia = 1.00;
            double multa = diasAtraso * valorMultaPorDia;

            // Certifica-se de que a multa seja pelo menos 0
            emprestimo.setMulta(Math.max(0, multa));
        } else {
            emprestimo.setMulta(0); // Sem multa se devolvido antes da data de devolução esperada
        }
        emprestimo.setSituacaoEmprestimo(SituacaoEmprestimo.DEVOLVIDO);
        //emprestimo.setDataDevolucao(LocalDate.ofEpochDay(11));
        //emprestimo.setSituacaoEmprestimo(SituacaoEmprestimo.DEVOLVIDO);

        //Passando o livro pra Disponivel novamente
        Livro livroDevolvido = emprestimo.getLivro();
        livroDevolvido.atualizarStatusParaDisponivel();


        return emprestimoRepository.save(emprestimo);
    }*/
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




