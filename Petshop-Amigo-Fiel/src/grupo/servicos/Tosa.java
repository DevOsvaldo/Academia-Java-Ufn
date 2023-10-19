package grupo.servicos;

public class Tosa extends Servicos{
    @Override
    public String getNome(){
        return "Tosa";
    }

    @Override
    public String descricao() {
        return "Serviço de Tosa para animais de estimação!";
    }

    @Override
    public double preco() {
        return 40.0;
    }
}
