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
        Scanner sc = new Scanner(System.in);
        List<Produto> listaProdutos = new ArrayList<>();
        List<Servicos> servicos = new ArrayList<>();

        System.out.println("***********************");
        System.out.println("**ENTRADA DE PRODUTOS**");
        System.out.println("***********************");
        System.out.println();
        System.out.println("-------------------------------------------------------");
        System.out.println("---BOAS VINDAS,  A INTERFACE DE CADASTRO DE PRODUTOS---");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.print("Insira a quantidade de produtos a serem cadastrados: ");
        int n = sc.nextInt();


        String tipo = "";
        for (int i = 0; i < n; i++) {
            System.out.println();
            sc.nextLine();//consumir linha anteriror
            String nome = " ";
            String categoria = "";
            System.out.println("******************************");
            System.out.print("Insira os dados correspondente: ");
            System.out.println();
            System.out.println("******************************");
            System.out.print("Categoria do produto: A - Ração; B-Brinquedo: ");
            char cat = sc.nextLine().toLowerCase().charAt(0);
            switch (cat) {
                case 'a':
                    categoria = "ração";
                    break;
                case 'b':
                    categoria = "brinquedo";
                    break;
                default:
                    System.out.println("Produto Invalido ou não faz parte do encarte");
            }
            if (cat != 'b') {

                System.out.println("Insira o tipo da ração: 1 - Cães e 2-Gatos");
                int entradaEstoque = sc.nextInt();
                sc.nextLine();
                switch (entradaEstoque) {
                    case 1:
                        System.out.print("1.Golden Cães, 2.DogChow, 3.Nero:  ");
                        int opcaoNome = sc.nextInt();
                        sc.nextLine();
                        nome = switch (opcaoNome) {
                            case 1 -> "Golden Cães";
                            case 2 -> "DogChow";
                            case 3 -> "Nero";
                            default -> nome;
                        };
                        break;
                    case 2:
                        System.out.println("1:Whiskas, 2:Golden Gatos, 3:Nutrilus: ");
                        int opcaoNome2 = sc.nextInt();
                        sc.nextLine();
                        switch (opcaoNome2) {
                            case 1:
                                nome = "Whiskas";
                                break;
                            case 2:
                                nome = "Golden-Gatos";
                            case 3:
                                nome = "Nutrilus";
                                break;
                            default:
                                System.out.println("Produto invalido");
                        }
                        break;
                    default:
                        System.out.println("Opçao inválida. Escolha uma opção válida.");
                }
            }
            if (cat == 'b') {
                nome = "Brinquedo p/ Pets";
            }

            System.out.print("Informe o preço do produto: ");
            double preco = sc.nextDouble();
            System.out.print("Cadastre também a quantidade de produtos: ");
            int quantidade = sc.nextInt();
            //consumir quebra de linha restante
            sc.nextLine();
            Produto produtos = new Produto(nome, categoria, preco, quantidade);

            listaProdutos.add(produtos);
        }


        System.out.println("*******************");
        System.out.println("********************");
        System.out.println("#APP# #PET-SHOP-AMIGO-FIEL");
        System.out.println("*******************");
        System.out.println("********************");
        System.out.println();


        System.out.println("**AREA DE CADASTRO DE PET'S**");
        System.out.println();
        System.out.print("Quantos bichinhos vamos cadastrar? ");
        int n2 = sc.nextInt();
        System.out.println();


        String especie = "";

        for (int i = 0; i < n2; i++) {
            sc.nextLine();
            System.out.println();
            System.out.print("Insira o nome do seu amiguinho fiel: ");
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
        }

        System.out.println("PET CADASTRADO COM SUCESSO!");
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
                    System.out.println("Serviço cadastrado");
                    break;
                case 2:
                    novoservicos = new Tosa();
                    servicos.add(novoservicos);
                    System.out.println("Serviço cadastrado");
                    break;
                case 3:
                    novoservicos = new ConsultaVeterinaria();
                    servicos.add(novoservicos);
                    System.out.println("Serviço cadastrado");
                    break;
                default:
                    System.out.println("Opção inválida");
            }



        for (Produto produtos : listaProdutos) {
            System.out.println("Descrição: " + produtos.getNome());
            System.out.println("Categoria: " + produtos.getCategoria());
            System.out.println("Preço: " + produtos.getPreco());
            System.out.println("Quantidade: " + produtos.getQuantitidadeEmEstoque());


        }

        for (Produto produtos : listaProdutos) {
            produtos.toString();
        }
        System.out.println("----------------------------");

        System.out.println();


        System.out.println("Produtos Definidos em sua respetiva categoria");
        for (int i = 0; i < n; i++) {

            System.out.println("Descrição do Produto: " + listaProdutos.get(i).getNome());
            System.out.println("Categoria: " + listaProdutos.get(i).getCategoria());
            System.out.println("Quantidade em Estoque: " + listaProdutos.get(i).getQuantitidadeEmEstoque());
        }

        System.out.println("***********************************************************************");
        System.out.println("************************************************************************");
        System.out.println("======BEM VINDO SR.CLIENTE=======");
        System.out.println("=====Escolha o produto======");
        while (true) {
            System.out.println("====Acessar Menu====");
            System.out.println("1.Comprar Produtos");
            System.out.println("2.Agendar Serviços");
            System.out.println("3.Verificar Serviços Agendados");
            System.out.println("4.Sair");

            //DECLARAÇÃO DE VARIÁVEIS - PARA FUNCIONAR FORA DO ESCOPO.
            int opcao = sc.nextInt();
            sc.nextLine();
            char tipoDeRacao = ' '; // Inicializa tipo de ração
            int escolhaMarca = 0;
            char escolhas = ' ';
            switch (opcao) {

                case 1:

                    System.out.println("Qual dos Produtos pretende comprar(A-Rações, B-Brinquedos)");
                    escolhas = sc.nextLine().toLowerCase().charAt(0);
                    if (escolhas == 'a') {
                        System.out.println("Escolha o tipo de ração(C-Cães, G-Gatos ):");
                        tipoDeRacao = sc.nextLine().toLowerCase().charAt(0);

                        List<String> marcasDisponiveis = new ArrayList<>();
                        //Marcas disponiveis
                        if (tipoDeRacao == 'c') {
                            marcasDisponiveis = Arrays.asList("Golden Cães", "DogChow", "Nero");
                        } else if (tipoDeRacao == 'g') {
                            marcasDisponiveis = Arrays.asList("Whiskas", "Golden Gatos", "Nutrilus");

                        }
                        System.out.println("Escolha uma das marcas disponíveis: ");
                        for (int i = 0; i < marcasDisponiveis.size(); i++) {
                            System.out.println((i + 1) + ": " + marcasDisponiveis.get(i));
                        }
                        escolhaMarca = sc.nextInt();
                        sc.nextLine();//consumir a quebra de linha pendente
                        if (escolhaMarca >= 1 && escolhaMarca <= marcasDisponiveis.size()) {
                            String marcaRacao = marcasDisponiveis.get(escolhaMarca - 1);
                            System.out.print("Quantidade de Produtos: ");
                            int quantidade = sc.nextInt();
                            sc.nextLine();
                            for (Produto produto : listaProdutos) {
                                if (produto.getCategoria().equals("ração") || produto.getCategoria().equals("racao")
                                ) {
                                    System.out.println(produto.getCategoria().equals("ração"));
                                    if (produto.vender(quantidade) == true) {
                                        System.out.println("Quantidade vendida: " + quantidade + " da Ração: " + marcaRacao);
                                    } else {
                                        System.out.println("Sem estoque do produto");
                                    }

                                }

                            }
                        }
                    } else if (escolhas == 'b') {
                        System.out.print("Quantidade de produtos: ");
                        int quantidade = sc.nextInt();
                        sc.nextLine();//consumir quebra de linha

                        for (Produto produtos : listaProdutos) {

                            if (produtos.getCategoria().equals("brinquedo")) {
                                if (produtos.vender(quantidade) == true) {
                                    System.out.println("Foram vendidos " + quantidade + " unidades de brinquedos com sucesso!");
                                } else {
                                    System.out.println("Sem Estoque!!");
                                }

                            }

                        }

                    } else {
                        System.out.println("Opção invalida");
                        System.out.println();
                    }


                    break;
                case 2:
                    System.out.println("***Escolha o serviço a ser Agendado***");
                    System.out.println("1:Banho, 2:Tosa, 3:Consulta Veterinária");

                    int escolhaServico1 = sc.nextInt();
                    sc.nextLine();
                    int n1 = 0;
                    switch (escolhaServico1) {
                        case 1:
                            System.out.println("Quantos pet's para este serviço, deseja cadastrar?");
                             n1 = sc.nextInt();
                            sc.nextLine();
                            for (int i = 0; i < n1; i++) {
                                System.out.print("Informe o nome do animal: ");
                                String nome = sc.nextLine();
                                String servico = "Banho";
                                System.out.println("Informe a data do serviço: ");
                                String data = sc.nextLine();
                                System.out.println("Informe um horário: ");
                                String horario = sc.nextLine();


                                Boolean horarioVago = false;
                                for (Agenda agendamento : Agenda.agendaList()) {
                                    if (agendamento.getData().equals(data) && agendamento.getHorario().equals(horario)) {
                                        horarioVago = true;
                                        break;
                                    }
                                }
                                if (horarioVago) {
                                    System.out.println("Horário Indisponível.Escolha um novo horário por favor! ");
                                } else {
                                    Agenda agenda1 = new Agenda(nome, servico, data, horario);
                                    Agenda.lista.add(agenda1);
                                    System.out.println("Serviço Agendado com sucesso!!");
                                }

                            }
                            break;
                        case 2:

                            for (int i = 0; i < n1; i++) {
                                System.out.print("Informe o nome do animal: ");
                                String nome = sc.nextLine();
                                String servico = "Tosa";
                                System.out.println("Informe a data do serviço: ");
                                String data = sc.nextLine();
                                System.out.println("Informe um horário: ");
                                String horario = sc.nextLine();


                                Boolean horarioVago = false;
                                for (Agenda agendamento : Agenda.agendaList()) {
                                    if (agendamento.getData().equals(data) && agendamento.getHorario().equals(horario)) {
                                        horarioVago = true;
                                        break;
                                    }
                                }
                                if (horarioVago) {
                                    System.out.println("Horário Indisponível.Escolha um novo horário por favor! ");
                                } else {
                                    Agenda agenda1 = new Agenda(nome, servico, data, horario);
                                    Agenda.lista.add(agenda1);
                                    System.out.println("Serviço Agendado com sucesso!!");
                                }

                            }
                            break;

                        case 3:
                            for (int i = 0; i < n1; i++) {
                                System.out.print("Informe o nome do animal: ");
                                String nome = sc.nextLine();
                                String servico = "Constula Veterinária";
                                System.out.println("Informe a data do serviço: ");
                                String data = sc.nextLine();
                                System.out.println("Informe um horário: ");
                                String horario = sc.nextLine();


                                Boolean horarioVago = false;
                                for (Agenda agendamento : Agenda.agendaList()) {
                                    if (agendamento.getData().equals(data) && agendamento.getHorario().equals(horario)) {
                                        horarioVago = true;
                                        break;
                                    }
                                }
                                if (horarioVago) {
                                    System.out.println("Horário Indisponível.Escolha um novo horário por favor! ");
                                } else {
                                    Agenda agenda1 = new Agenda(nome, servico, data, horario);
                                    Agenda.lista.add(agenda1);
                                    System.out.println("Serviço Agendado com sucesso!!");
                                }

                            }
                            break;
                        default:
                            System.out.println("Opção INVALIDA");
                    }
                case 3:
                    List<Agenda> lista = Agenda.agendaList();
                    if(lista.isEmpty()){
                        System.out.println("Nenhum Serviço solicitado");
                    } else{
                        for (Agenda agendamento : Agenda.agendaList()) {
                            agendamento.getAnimal();
                            agendamento.getServicos();
                            agendamento.getData();
                            agendamento.getHorario();
                            System.out.println(""+agendamento.toString());
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

        }
    }
}



