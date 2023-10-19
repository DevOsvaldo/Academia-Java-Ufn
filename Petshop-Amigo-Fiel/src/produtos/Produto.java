package produtos;




public  class Produto {
    private String nome;
    private String categoria;
    private double preco;
    private int quantitidadeEmEstoque;




    public Produto(String nome, String categoria, double preco, int quantitidadeEmEstoque) {
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.quantitidadeEmEstoque = quantitidadeEmEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantitidadeEmEstoque() {
        return quantitidadeEmEstoque;
    }

    public void setQuantitidadeEmEstoque(int quantitidadeEmEstoque) {
        this.quantitidadeEmEstoque = quantitidadeEmEstoque;
    }

    public boolean temNoEstoque(int quantidade) {
        if (quantitidadeEmEstoque != 0 && quantitidadeEmEstoque > quantidade) {

            return true;
        }
        return false;
    }


    public boolean vender(int quantidade) {
        if (quantidade > 0 && quantidade <= this.quantitidadeEmEstoque) {
            this.quantitidadeEmEstoque -= quantidade;

            System.out.println(quantidade + " unidades de " + nome + " vendidos com sucesso!");
            System.out.println(toString());
            return true;

        } else {
            System.out.println("Não tem no estoque");
            return false;
        }

        }


    @Override
    public String toString() {
        return String.format("Produto{" +
                "nome: " + nome + "," + "categoria: " + categoria + ", preco:" + String.format("%.2f",preco) ) +", quantitidadeEmEstoque:" + quantitidadeEmEstoque;
    }
}




