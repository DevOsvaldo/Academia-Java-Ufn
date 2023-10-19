package agendamento;





import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private String animal;
    private String servicos;
    private String data;
    private String horario;
    public static List<Agenda> lista = new ArrayList<Agenda>();
    public Agenda(String animal, String servicos, String data, String horario) {
        this.animal = animal;
        this.servicos = servicos;
        this.data = data;
        this.horario = horario;
    }
    public Agenda(){}
    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getServicos() {
        return servicos;
    }

    public void setServicos(String servicos) {
        this.servicos = servicos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }


    public static List<Agenda> agendaList() {
        return lista;
    }

    @Override
    public String toString() {
        return "Agendamento: "+"Pet: "+animal+", servicos: "+servicos+",data:" + data +", horario:"+ horario;
    }

}


















