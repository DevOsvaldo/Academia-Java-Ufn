package application;

import produtos.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoInfo {
    private List<Produto> listaProdutos;

    public ProdutoInfo() {
        listaProdutos = new ArrayList<>();
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }
    public String listarItens(List<?> itens, String titulo) {
        StringBuilder result = new StringBuilder();
        result.append("==================================\n");
        result.append("      ").append(titulo).append("     \n");
        result.append("==================================");

        for(int i =0; i < itens.size(); i++) {
            result.append("\nÍndice: ").append(i + 1).append("\n");
            result.append(itens.get(i).toString());
            result.append("\n==================================");
        }
        return result.toString();
    }
}
