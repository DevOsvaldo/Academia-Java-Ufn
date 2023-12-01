package com.gerenciamentobiblioteca.GerenciamentoBiblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
    /*@ManyToOne
    private Multa multa;*/
    @Temporal(TemporalType.DATE)
    private LocalDate dataEmprestimo;
    @Temporal(TemporalType.DATE)
    private LocalDate dataDevolucao;
    @Enumerated(EnumType.STRING)
    private SituacaoEmprestimo situacaoEmprestimo;




    public void aplicarMulta(){
        LocalDate dataDevolucao = getDataDevolucao();
        LocalDate dataDevolucaoEstipulada = LocalDate.now().plusDays(7); // Exemplo: devolução em até 7 dias
        if(dataDevolucao.isAfter(dataDevolucaoEstipulada)){
            long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoEstipulada, dataDevolucao);
            double multa = diasAtraso * 5.0;// Multa de R$5.00 por dias atrasado
           //atualizarMulta(multa);

            System.out.println("A multa foi de: "+multa);
        }
    }
    /*
    public void atualizarMulta(double novaMulta) {
        if (multa == null) {
            multa = new Multa();
        }
        multa.setValor(novaMulta);
        multa.setMotivo("Atraso");
    }

    public void setMulta(double multa) {
        atualizarMulta(multa);
    }*/
}
