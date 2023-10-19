package recebimento.animais;

import agendamento.Agenda;

import java.util.ArrayList;
import java.util.List;

public  class Animal {
    private String nome;
    private String especie;
    private String raca;
    private String data_de_nascimento;
    private String proprietario;

    public static List<Animal> listaAnimal = new ArrayList<Animal>();
    public Animal(String nome, String especie, String raca, String data_de_nascimento, String proprietario) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.data_de_nascimento = data_de_nascimento;
        this.proprietario = proprietario;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getData_de_nascimento() {
        return data_de_nascimento;
    }

    public void setData_de_nascimento(String data_de_nascimento) {
        this.data_de_nascimento = data_de_nascimento;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }


    public String fazerBarulho(){
        return "Barulho característico do animal";
    }

    public String exibirPerfil(){
        String info = "Nome: "+getNome()+
                ", Especie: "+getEspecie()
                +", Raça: "+ getRaca()+
                ", Data de Nascimento: "+getData_de_nascimento()
                +", Proprietario: "+getProprietario();
        return info;
    }
public static List<Animal> animalList(){
        return listaAnimal;
}

}
