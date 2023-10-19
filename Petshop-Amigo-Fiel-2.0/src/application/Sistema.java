package application;


import agendamento.Agenda;
import grupo.servicos.Banho;
import grupo.servicos.ConsultaVeterinaria;
import grupo.servicos.Servicos;
import grupo.servicos.Tosa;
import produtos.Produto;
import recebimento.animais.Animal;


import java.util.*;


public class Sistema {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        ProdutoInfo produtoInfo = new ProdutoInfo();
        ServicoInfo servicoInfo = new ServicoInfo();

        int tipoUsuario;

        do {
            System.out.print("|-----------------------------|\n");
            System.out.print("| 1 - Funcionário             |\n");
            System.out.print("| 2 - Cliente                 |\n");
            System.out.print("| 0 - Sair                    |\n");
            System.out.print("|-----------------------------|\n");
            System.out.print("Digite uma opção do menu: ");
            tipoUsuario = sc.nextInt();

            switch (tipoUsuario) {
                case 1: // Funcionário
                    funcionarioMenu(sc, produtoInfo, servicoInfo);
                    break;
                case 2: // Cliente
                    clienteMenu(sc, produtoInfo, servicoInfo);
                    break;
                case 0: // Sair
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (tipoUsuario != 0);
    }



    private static void clienteMenu(Scanner sc,  ProdutoInfo produtoInfo, ServicoInfo servicoInfo) {
        System.out.println("***********************************************************************");
        System.out.println("************************************************************************");
        System.out.println("                    ======BEM VINDO SR.CLIENTE=======");
        System.out.println("*************************************************************************");
        System.out.println("*************************************************************************");
        System.out.println("*                      #APP# #PET-SHOP-AMIGO-FIEL                       *");
        System.out.println("*                                                                       *");
        System.out.println("*************************************************************************");
        System.out.println();
        while (true) {
            System.out.println("====Acessar Menu====");
            System.out.println("1.Comprar Produtos");
            System.out.println("2.Agendar Serviços");
            System.out.println("3.Verificar Serviços Agendados");
            System.out.println("4.Sair");


            int opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {

                case 1:
                    venderProduto(sc,produtoInfo);

                case 2:
                    System.out.println("***Escolha o serviço a ser Agendado***");
                    System.out.println("1: Banho, 2: Tosa, 3: Consulta Veterinária");
                    int escolhaServico = sc.nextInt();
                    sc.nextLine();


                    switch (escolhaServico) {
                        case 1:
                            System.out.print("Informe o nome do animal: ");
                            String nomeBanho = sc.nextLine();
                            String servicoBanho = "Banho";
                            System.out.print("Informe a data do serviço: ");
                            String dataBanho = sc.nextLine();
                            System.out.print("Informe um horário: ");
                            String horarioBanho = sc.nextLine();

                            boolean horarioVago = false;
                            for (Agenda agendamento : Agenda.agendaList()) {
                                if (agendamento.getData().equals(dataBanho) && agendamento.getHorario().equals(horarioBanho)) {
                                    horarioVago = true;
                                    break;
                                }
                            }

                            if (horarioVago) {
                                System.out.println("Horário Indisponível. Escolha um novo horário por favor!");
                            } else {
                                Agenda agenda = new Agenda(nomeBanho, servicoBanho, dataBanho, horarioBanho);
                                Agenda.lista.add(agenda);
                                System.out.println("Serviço Agendado com sucesso!!");
                            }
                            break;
                        case 2: // Tosa
                            System.out.print("Informe o nome do animal: ");
                            String nomeTosa = sc.nextLine();
                            String servicoTosa = "Tosa";

                            System.out.print("Informe a data do serviço: ");
                            String dataTosa = sc.nextLine();
                            System.out.print("Informe um horário: ");
                            String horarioTosa = sc.nextLine();

                            if (verificarDisponibilidade(servicoTosa, dataTosa, horarioTosa)) {
                                Agenda agendaTosa = new Agenda(nomeTosa, servicoTosa, dataTosa, horarioTosa);
                                Agenda.lista.add(agendaTosa);
                                System.out.println("Serviço de Tosa Agendado com sucesso!");
                            } else {
                                System.out.println("Horário Indisponível. Escolha um novo horário por favor!");
                            }
                            break;

                        case 3: // Consulta Veterinária
                            System.out.print("Informe o nome do animal: ");
                            String nomeConsulta = sc.nextLine();
                            String servicoConsulta = "Consulta Veterinária";

                            System.out.print("Informe a data do serviço: ");
                            String dataConsulta = sc.nextLine();
                            System.out.print("Informe um horário: ");
                            String horarioConsulta = sc.nextLine();

                            if (verificarDisponibilidade(servicoConsulta, dataConsulta, horarioConsulta)) {
                                Agenda agendaConsulta = new Agenda(nomeConsulta, servicoConsulta, dataConsulta, horarioConsulta);
                                Agenda.lista.add(agendaConsulta);
                                System.out.println("Serviço de Consulta Veterinária Agendado com sucesso!");
                            } else {
                                System.out.println("Horário Indisponível. Escolha um novo horário por favor!");
                            }
                            break;


                        default:
                            System.out.println("Opção INVALIDA");
                    }
                case 3:
                    List<Agenda> lista = Agenda.agendaList();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum Serviço solicitado");
                    } else {
                        for (Agenda agendamento : Agenda.agendaList()) {
                            agendamento.getAnimal();
                            agendamento.getServicos();
                            agendamento.getData();
                            agendamento.getHorario();
                            System.out.println("" + agendamento.toString());
                        }
                    }


                    break;
                case 4:
                    System.out.println("Finalizando atividades...");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Escolha uma opção válida. ");
            }
            break;

        }

    }


    private static boolean verificarDisponibilidade(String servicoTosa, String dataTosa, String horarioTosa) {
        for (Agenda agendamento : Agenda.agendaList()) {
            if (agendamento.getServicos().equals(servicoTosa) && agendamento.getData().equals(dataTosa) && agendamento.getHorario().equals(horarioTosa)) {
                return false; // Horário já agendado
            }
        }
        return true; // Horário disponível
    }


    private static void funcionarioMenu(Scanner sc, ProdutoInfo produtoInfo, ServicoInfo servicoInfo) {
        List<Produto> listaProdutos = produtoInfo.getListaProdutos();
        List<Servicos> servicos = servicoInfo.getServicos();
        int opcaoMenu;

        while (true) {
            System.out.print("##--MENU DE ACESSO FUNCIONÁRIO--##\n\n");
            System.out.print("|-----------------------------|\n");
            System.out.print("| Opção 1 - Cadastro Produtos |\n");
            System.out.print("| Opção 2 - Cadastro Animais  |\n");
            System.out.print("| Opção 3 - Cadastro Serviços |\n");
            System.out.print("| Opção 4 - Menu Cliente      |\n");
            System.out.print("| Opção 5 - Sair              |\n");
            System.out.print("|-----------------------------|\n");
            System.out.print("Digite uma opção: ");

            opcaoMenu = sc.nextInt();
            switch (opcaoMenu) {
                case 1:
                    System.out.print("\nCadastro de Produto Selecionado");
                    System.out.println();
                    System.out.println("************************************");
                    System.out.println("**        ENTRADA DE PRODUTOS     **");
                    System.out.println("************************************");
                    System.out.println();
                    System.out.println("-------------------------------------------------------");
                    System.out.println("---BOAS VINDAS,  A INTERFACE DE CADASTRO DE PRODUTOS---");
                    System.out.println("-------------------------------------------------------");
                    System.out.println();
                    System.out.print("Insira a quantidade de produtos a serem cadastrados: ");
                    int n = sc.nextInt();
                    for(int i = 0; i < n; i++) {
                        System.out.println("Informe o tipo do produto: 1 - Ração Cães, 2 - Ração Gatos," +
                                "3-Brinquedos em Geral");
                        sc.nextLine();
                        System.out.print("Digite uma opção: ");
                       int escolhaTipo = sc.nextInt();
                        sc.nextLine();
                        switch (escolhaTipo){
                            case 1:
                                System.out.println("Ração para Cães, trabalhamos com 3 tipos: 1.Golden Cães," +
                                        " 2.DogChow e 3.Nero");
                                int racaoCao = sc.nextInt();
                                sc.nextLine();
                                switch (racaoCao){
                                    case 1:
                                        listaProdutos.add(new Produto("Ração Golden Cães", "Ração",
                                                139.99, 100));
                                        break;
                                    case 2:
                                        listaProdutos.add(new Produto("Ração DogChow", "Ração",
                                                160.99, 50));
                                        break;
                                    case 3:
                                        listaProdutos.add(new Produto("Ração Nero - Carne", "Ração",
                                                89.99, 30));
                                        break;
                                    default:
                                        System.out.println("Lamento, porem só trabalhamos com as opções oferecidas.");
                                }
                        break;
                            case 2:
                                System.out.println("Ração para Cães, trabalhamos com 3 tipos: 1.Golden Gatos, " +
                                        "2.Whiskas e 3.Nutrilus");
                                int racaoGato = sc.nextInt();
                                switch (racaoGato){
                                    case 1:
                                        listaProdutos.add(new Produto("Ração Golden Gatos", "Ração",
                                                153.99, 110));
                                        break;
                                    case 2:
                                        listaProdutos.add(new Produto("Ração Whiskas", "Ração",
                                                190.99, 50));
                                        break;
                                    case 3:
                                        listaProdutos.add(new Produto("Ração Nutrilus", "Ração",
                                                160.99, 200));
                                        break;
                                    default:
                                        System.out.println("Lamento, porem só trabalhamos com as opções oferecidas.");
                                }
                            case 3:
                                System.out.println("Brinquedos para pet's temos: ");
                                listaProdutos.add(new Produto("Brinquedo Bola com Corda", "Brinquedo",
                                        59.99, 100));
                                listaProdutos.add(new Produto("Ossos Mastigável", "Brinquedo",
                                        30.99, 100));
                            break;
                            default:
                                System.out.println("Retorne ao menu, e escolha outra opção.");


                    }




                    }

                    System.out.println("Produtos Definidos em sua respetiva categoria");
                    for (int i = 0; i < listaProdutos.size(); i++) {

                        System.out.println("Descrição do Produto: " + listaProdutos.get(i).getNome());
                        System.out.println("Categoria: " + listaProdutos.get(i).getCategoria());
                        System.out.println("Quantidade em Estoque: " + listaProdutos.get(i).getQuantitidadeEmEstoque());
                    }
                    break;

                case 2:
                    System.out.print("\nCadastro de Pet selecionado: \n");
                    System.out.println("**AREA DE CADASTRO DE PET'S**");
                    System.out.println();
                    System.out.print("Quantos Pet's vamos cadastrar? ");
                    int n2 = sc.nextInt();
                    System.out.println();


                    String especie = "";

                    for (int i = 0; i < n2; i++) {
                        sc.nextLine();
                        System.out.println();
                        System.out.print("Insira o nome do Pet: ");
                        String nomePet = sc.nextLine();
                        System.out.print("Informe a especie: ");
                        especie = sc.nextLine().toLowerCase();
                        System.out.print("Informe a raça: ");
                        String raca = sc.nextLine();
                        System.out.print("Informe a data de nascimento do seu pet: ");
                        String dataNascimento = sc.nextLine();
                        System.out.print("Informe o nome do proprietário do pet: ");
                        String nomeProprietario = sc.nextLine();


                        Animal animal = new Animal(nomePet, especie, raca, dataNascimento, nomeProprietario);

                        List<Animal> listaAnimal = new ArrayList<>();
                        listaAnimal.add(animal);
                        System.out.println("PET CADASTRADO COM SUCESSO!");
                    }

                    break;

                case 3:
                    System.out.print("\nCadastro de Serviços Selecionado\n");
                    System.out.println("*******************************************");
                    System.out.println("***Escolha o serviço a ser cadastrado***");
                    System.out.println("1:Banho, 2:Tosa, 3:Consulta Veterinária");
                    Servicos novoservicos;
                    int escolhaServico = sc.nextInt();
                    sc.nextLine();
                    switch (escolhaServico) {
                        case 1:
                            novoservicos = new Banho();
                            servicos.add(novoservicos);
                            System.out.println("Serviço cadastrado ");
                            break;
                        case 2:
                            novoservicos = new Tosa();
                            servicos.add(novoservicos);
                            System.out.println("Serviço cadastrado ");
                            break;
                        case 3:
                            novoservicos = new ConsultaVeterinaria();
                            servicos.add(novoservicos);
                            System.out.println("Serviço cadastrado ");
                            break;
                        default:
                            System.out.println("Opção inválida");
                    }
                    break;
                case 4:
                    clienteMenu(sc, produtoInfo, servicoInfo);
                    break;
                    case 5:
                        System.out.println("Finalizando atividades...");

                        sc.close();
                        System.exit(0);
                        break;
                default:
                    System.out.print("\nOpção Inválida!");
            }

        }
    }


    private static void venderProduto(Scanner sc, ProdutoInfo produtoInfo) {
        System.out.println(produtoInfo.listarItens(produtoInfo.getListaProdutos(), "PRODUTOS DISPONÍVEIS"));

        System.out.println("Informe o código do produto: ");
        int cdProduto = sc.nextInt();

        if (cdProduto < 0 || cdProduto > produtoInfo.getListaProdutos().size()) {
            throw new InputMismatchException("Não existe esse código informado.");
        }

        System.out.println("\nInforme a quantidade que deseja comprar");
        int quantidade = sc.nextInt();

        Produto produtos = produtoInfo.getListaProdutos().get(cdProduto - 1);
        if (produtos.vender(quantidade)) {
            System.out.println("Produto vendido com sucesso!");
            System.out.println("Valor total R$: " + quantidade * produtos.getPreco());
        } else {
            System.out.println("O produto não possui quantidade em estoque suficiente.");
        }
    }
}















