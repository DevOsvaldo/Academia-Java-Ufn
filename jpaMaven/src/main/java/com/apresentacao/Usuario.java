package com.apresentacao;

import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_usuario;
    String nome;
    String endereco;
    String email;
    String telefone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_administracao", updatable = false, insertable = false)
    private Administracao administracao;
    public Usuario() {
    }
    public Usuario(Integer id_usuario, String nome, String endereco, String email, String telefone, Administracao administracao ) {
        super();
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
        this.administracao = administracao;
    }

    public Integer getId_paciente() {
        return id_usuario;
    }

    public void setId_paciente(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public Administracao getAdministracao() {
        return administracao;
    }

    public void setAdministracao(Administracao administracao) {
        this.administracao = administracao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id_usuario=" + id_usuario +
                ", nome='" + nome + '\'' +
                ", endereço='" + endereco + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
