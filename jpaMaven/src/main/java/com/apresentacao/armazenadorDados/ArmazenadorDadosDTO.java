package com.apresentacao.armazenadorDados;



import javax.persistence.*;




public class ArmazenadorDadosDTO {
    private Integer administracaoId;
    private String administracaoNome;
    private Integer usuarioId;
    private String usuarioNome;
    private String administracao_id;


    public Integer getAdministracaoId() {
        return administracaoId;
    }

    public void setAdministracaoId(Integer administracaoId) {
        this.administracaoId = administracaoId;
    }

    public String getAdministracaoNome() {
        return administracaoNome;
    }

    public void setAdministracaoNome(String administracaoNome) {
        this.administracaoNome = administracaoNome;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getAdministracao_id() {
        return administracao_id;
    }

    public void setAdministracao_id(String administracao_id) {
        this.administracao_id = administracao_id;
    }
}
