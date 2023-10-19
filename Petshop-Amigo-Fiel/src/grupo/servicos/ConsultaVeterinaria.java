package grupo.servicos;

public class ConsultaVeterinaria extends Servicos{


    @Override
    public String descricao() {
        return "Atendimento médico para o seu bichinho de estimação, afinal nada melhor do que seu melhor amigo saudável.";
    }
    @Override
    public String getNome(){
        return "Consulta";
    }
    @Override
    public double preco() {
        return 100.00;
    }
}
