package application;

import grupo.servicos.Servicos;

import java.util.ArrayList;
import java.util.List;

public class ServicoInfo {
    private List<Servicos> servicos;

    public ServicoInfo() {
        servicos = new ArrayList<>();
    }

    public List<Servicos> getServicos() {
        return servicos;
    }
}
