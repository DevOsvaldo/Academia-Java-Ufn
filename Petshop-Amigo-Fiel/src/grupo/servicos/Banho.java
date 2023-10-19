package grupo.servicos;

public class Banho extends Servicos{
    public Banho() {

    }

    public String getNome(){
        return "Banho";
    }



    @Override
    public String descricao(){
        return "Serviço de banho para animais de estimação!";
    }
    @Override
    public double preco(){
        return 25.00;
    }



}
