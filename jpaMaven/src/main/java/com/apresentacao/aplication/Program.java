package com.apresentacao.aplication;


import com.apresentacao.Administracao;
import com.apresentacao.Usuario;
import com.apresentacao.armazenadorDados.ArmazenadorDadosDTO;
import com.apresentacao.jpautil.JPAUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        Scanner sc = new Scanner(System.in);
        int opcaoMenu;
        do {
            System.out.print("|-----------------------------|\n");
            System.out.print("|-----SISTEMA DE CADASTRO-----|\n");
            System.out.print("|------CLINICA MULHER DEV-----|\n");
            System.out.print("|-----------------------------|\n");
            System.out.print("| 1 - Cadastro Administrativo |\n");
            System.out.print("| 2 - Cadastro Usuario        |\n");
            System.out.print("| 3 - Consultar Nome/Setor    |\n");
            System.out.print("| 4 - Alterar Dados           |\n");
            System.out.print("| 5 - Remover Dados           |\n");
            System.out.print("| 0 - Sair                    |\n");
            System.out.print("|-----------------------------|\n");
            //System.out.print("Digite uma opção do menu: ");
            while (true) {
                System.out.print("Digite uma opção do menu: ");

                // Verifica se a entrada é um número inteiro
                if (sc.hasNextInt()) {
                    opcaoMenu = sc.nextInt();
                    sc.nextLine();  // Limpar o buffer do teclado

                    // Verifica se o número está dentro da faixa desejada
                    if (opcaoMenu >= 0 && opcaoMenu <= 5) {
                        break;  // Sai do loop se a entrada for válida
                    } else {
                        System.out.println("Por favor, digite um número de 0 a 5.");
                    }
                } else {
                    // Se a entrada não for um número inteiro, consome a entrada inválida e pede novamente
                    System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                    sc.nextLine();
                }
            }

            // Agora, opcaoMenu contém um valor inteiro válido dentro da faixa desejada.
            System.out.println("Você escolheu a opção: " + opcaoMenu);
            switch (opcaoMenu) {
                case 1:
                    adminCadastro();
                    break;
                case 2:
                    inserirDados();
                    break;
                case 3:
                    consultar();
                    break;
                case 4:
                    alterarDados();
                    break;
                case 5:
                    removerCadastro();
                    break;
                default:
                    if(opcaoMenu  == 0){
                        System.out.println("Sair Menu");
                    } else if (opcaoMenu < 0 || opcaoMenu > 5) {
                        System.out.println("Opção invalida");
                    }

            }
        } while (opcaoMenu != 0);

        System.out.println("SISTEMA FINALIZANDO......");
        JPAUtil.closeEntityManagerFactory();

    }

    public static void adminCadastro() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Informe a Ocupação:  ");
        String nome = scan.nextLine();
        System.out.print("Informe o Setor(Ex:Administração): ");
        String tipo = scan.nextLine();

        EntityManager em = JPAUtil.getEntityManager();
        try {
            Administracao admin = new Administracao();
            admin.setNome(nome);
            admin.setTipoUsuario(tipo);
            List<Administracao> adminPerfil = new ArrayList<>();
            adminPerfil.add(admin);
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
            System.out.println("Cadastro Administrativo Realizado com Sucesso!");
        } catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            em.close();
        }



    }
    public static void inserirDados() {
        Scanner scan = new Scanner(System.in);
        System.out.println("*** Cadastro de usuário ***");
        System.out.print("Informe o nome completo: ");
        String nome = scan.nextLine();
        System.out.print("Informe o e-mail: ");
        String email = scan.nextLine();
        System.out.print("Informe o seu endereço: ");
        String endereco = scan.nextLine();
        System.out.print("Informe o numero de telefone: ");
        String telefone = scan.nextLine();
        System.out.println("**************************");

        Administracao admin;
        try {
            EntityManager em = JPAUtil.getEntityManager();
            System.out.println("1-Médico, 2-Administrador, 3-Paciente, 4-Assistente Administrativo");
            System.out.print("Informe o setor que esse usuário pertencerá: ");
            int setor = scan.nextInt();
            scan.nextLine();
            if (setor > 4) {
                System.out.println("Setor invalido");
            } else {
                admin = em.find(Administracao.class, setor);
                Usuario user = new Usuario();
                user.setNome(nome);
                user.setEmail(email);
                user.setEndereco(endereco);
                user.setTelefone(telefone);
                user.setAdministracao(admin);
                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();
                System.out.println("Usuário cadastrado!");
            }
            em.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void consultar() {
        Scanner scan = new Scanner(System.in);

        EntityManager em = JPAUtil.getEntityManager();

        System.out.print("Informe o id da consulta: ");
        int id = scan.nextInt();

        // Buscar a entidade Administracao diretamente pelo ID
        Administracao administracao = em.find(Administracao.class, id);

        if (administracao != null) {
            List<ArmazenadorDadosDTO> dtos = new ArrayList<>();

            for (Usuario usuario : administracao.getUsuarios()) {
                ArmazenadorDadosDTO dto = new ArmazenadorDadosDTO();
                dto.setAdministracaoNome(administracao.getNome());
                dto.setUsuarioNome(usuario.getNome());
                dtos.add(dto);
            }
            //System.out.println("Nome do Usuário: "+);
            //System.out.println("Classificação: " + classificacao);
            System.out.println("--------------------");
            // Faça o que precisar com a lista de dtos
            for (ArmazenadorDadosDTO users : dtos){
                System.out.println("Usuario:"+users.getUsuarioNome());
                System.out.println("Classificação: "+ users.getAdministracaoNome());
            }
        } else {
            System.out.println("Nenhum perfil Administração encontrada para o ID informado.");
        }

        em.close();

    }

    public static void alterarDados() {
        Scanner scan = new Scanner(System.in);
        int opcaoMenu;
        try {
            EntityManager em = JPAUtil.getEntityManager();
        do {
            System.out.println("Qual das tabelas terá os dados modificados: ");
            System.out.print("|*****************************|\n");
            System.out.print("| 1 - Table Usuarios          |\n");
            System.out.print("| 2 - Table Administracao     |\n");
            System.out.print("| 0 - Sair                    |\n");
            System.out.print("|*****************************|\n");
            while (true) {
                System.out.print("Digite uma opção do menu: ");

                // Verifica se a entrada é um número inteiro
                if (scan.hasNextInt()) {
                    opcaoMenu = scan.nextInt();
                    scan.nextLine();  // Limpar o buffer do teclado

                    // Verifica se o número está dentro da faixa desejada
                    if (opcaoMenu >= 0 && opcaoMenu <= 2) {
                        break;  // Sai do loop se a entrada for válida
                    } else {
                        System.out.println("Por favor, digite um número de 0 a 2.");
                    }
                } else {
                    // Se a entrada não for um número inteiro, consome a entrada inválida e pede novamente
                    System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                    scan.nextLine();
                }
            }

            // Agora, opcaoMenu contém um valor inteiro válido dentro da faixa desejada.
            System.out.println("Você escolheu a opção: " + opcaoMenu);
              switch (opcaoMenu) {
                    case 1:
                        Usuario user;
                        System.out.print("Informe o id do perfil a ser modificado: ");
                        int opcaoMenu1;
                        int id = scan.nextInt();
                        scan.nextLine();
                        user = em.find(Usuario.class, id);
                        System.out.println(user.toString());
                        if (user == null) {
                            opcaoMenu = 0;
                            System.out.println("Usuário não existe");//Verificador se o id existe

                        }else{


                       do {
                        System.out.println("Qual dos dados serão modificado(os): ");
                        System.out.print("|*****************************|\n");
                        System.out.print("| 1 - Nome                    |\n");
                        System.out.print("| 2 - E-mail                  |\n");
                        System.out.print("| 3 - Endereço                |\n");
                        System.out.print("| 4 - Telefone                |\n");
                        System.out.print("| 5 - Setor                   |\n");
                        System.out.print("| 6 - Todos                   |\n");
                        System.out.print("| 0 - Sair                    |\n");
                        System.out.print("|*****************************|\n");
                        System.out.print("Digite uma opção do menu: ");
                       opcaoMenu1 = scan.nextInt();
                        scan.nextLine();
                        switch (opcaoMenu1){
                            case 1:
                                System.out.print("Informe o nome completo: ");
                                user.setNome(scan.nextLine());
                                break;
                            case 2:
                                System.out.print("Informe o e-mail:");
                                user.setEmail(scan.nextLine());
                                break;
                            case 3:
                                System.out.print("Informe o Endereço:");
                                user.setEndereco(scan.nextLine());
                                break;
                            case 4:
                                System.out.print("Informe o telefone:");
                                user.setTelefone(scan.nextLine());
                                break;
                            case 5:
                                System.out.println("1-Médico, 2-Administrador, 3-Assistente, 4-Paciente");
                                System.out.print("Informe o setor: ");
                                int set = scan.nextInt();
                                scan.nextLine();
                                Administracao admin;
                                admin = em.find(Administracao.class, set);
                                user.setAdministracao(admin);
                                break;
                            case 6:
                                System.out.print("Informe o nome completo: ");
                                String nome = scan.nextLine();
                                System.out.print("Informe o e-mail: ");
                                String email = scan.nextLine();
                                System.out.print("Informe o seu endereço: ");
                                String endereco = scan.nextLine();
                                System.out.print("Informe o numero de telefone: ");
                                String telefone = scan.nextLine();
                                System.out.println("**************************");
                                System.out.println("1-Médico, 2-Administrador, 3-Assistente, 4-Paciente");
                                System.out.print("Informe o setor que esse usuário pertencerá: ");
                                Integer setor = scan.nextInt();
                                scan.nextLine();
                                admin = em.find(Administracao.class, setor);
                                user.setNome(nome);
                                user.setEmail(email);
                                user.setEndereco(endereco);
                                user.setTelefone(telefone);
                                user.setAdministracao(admin);
                                break;
                            default:
                                if(opcaoMenu  == 0){
                                    System.out.println("Sair Menu");
                                } else if (opcaoMenu < 0 || opcaoMenu > 6) {
                                    System.out.println("Opção invalida");
                                }

                        }
                       }while (opcaoMenu1 != 0);
                            em.getTransaction().begin();
                            em.persist(user);
                            em.getTransaction().commit();
                        }


                        break;
                    case 2:
                        Administracao admin1;
                        System.out.print("Informe o id do perfil a ser modificado: ");
                        int id1 = scan.nextInt();
                        scan.nextLine();
                        int opcaoAdmin;
                        admin1 = em.find(Administracao.class, id1);
                        if(admin1 == null){
                            System.out.println("PERIFL NÃO EXISTE");
                        }else{
                       do {
                        System.out.println("Qual dos dados serão modificado(os): ");
                        System.out.print("|*****************************|\n");
                        System.out.print("| 1 - Ocupação                |\n");
                        System.out.print("| 2 - Setor                   |\n");
                        System.out.print("| 3 - Todos                   |\n");
                        System.out.print("| 0 - Sair                    |\n");
                        System.out.print("|*****************************|\n");
                        System.out.print("Digite uma opção do menu: ");
                        opcaoAdmin = scan.nextInt();
                        scan.nextLine();
                        switch (opcaoAdmin){
                            case 1:
                                System.out.print("Informe a ocupação: ");
                                admin1.setNome(scan.nextLine());
                                break;
                            case 2:
                                System.out.print("Informe o Setor(Ex:Administração)");
                                admin1.setTipoUsuario(scan.nextLine());
                                break;
                            case 3:
                                System.out.print("Informe a ocupação: ");
                                admin1.setNome(scan.nextLine());
                                System.out.print("Informe o Setor(Ex:Administração)");
                                admin1.setTipoUsuario(scan.nextLine());
                                break;
                            default:
                                if(opcaoMenu  == 0){
                                    System.out.println("Sair Menu");
                                } else if (opcaoMenu < 0 || opcaoMenu > 3) {
                                    System.out.println("Opção invalida");
                                }
                        }

                       }while (opcaoAdmin != 0);
                        em.getTransaction().begin();
                        em.persist(admin1);
                        em.getTransaction().commit();
                        em.close();
                        }
                        break;
                    default:
                      if(opcaoMenu  == 0){
                          System.out.println("Sair Menu");
                      } else if (opcaoMenu < 0 || opcaoMenu > 2) {
                          System.out.println("Opção invalida");
                      }

                }
            } while (opcaoMenu != 0) ;

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void removerCadastro(){
        Scanner scan = new Scanner(System.in);
        int opcaoMenu;
        try{
            EntityManager em = JPAUtil.getEntityManager();

        do {
            System.out.println("Qual das tabelas terá os dados removidos: ");
            System.out.print("|*****************************|\n");
            System.out.print("| 1 - Table Usuarios          |\n");
            System.out.print("| 2 - Table Administracao     |\n");
            System.out.print("| 0 - Sair                    |\n");
            System.out.print("|*****************************|\n");
            System.out.print("Digite uma opção do menu: ");
            opcaoMenu = scan.nextInt();
            scan.nextLine();
            switch (opcaoMenu){
                case 1:
                    Usuario user;
                    System.out.print("Informe o id do perfil a ser removido: ");
                    Integer id = scan.nextInt();
                    scan.nextLine();
                    user = em.find(Usuario.class,id);
                    System.out.print(user+"\n");
                    System.out.println("Este é o perfil que deseja remover? S - Sim ou N - Não");
                    char resp = scan.nextLine().charAt(0);
                    while (resp != 'S' && resp != 's'){
                        System.out.println("ok!");
                        System.out.print("Informe o id do perfil a ser removido: ");
                        int id2 = scan.nextInt();
                        scan.nextLine();
                        user = em.find(Usuario.class,id2);
                        System.out.println("Ok! então este perfil será removido");
                        try {
                            em.getTransaction().begin();
                            em.remove(user);
                            em.getTransaction().commit();
                        }catch (Exception e){
                            if (em.getTransaction().isActive()) {
                                em.getTransaction().rollback();
                            }
                            e.printStackTrace();
                        }

                        System.out.println("Dados deletados com sucesso!");
                        break;
                    }
                    try {
                        em.getTransaction().begin();
                        em.remove(user);
                        em.getTransaction().commit();
                        System.out.println("Dados deletados com sucesso!");
                    }catch (Exception e){
                        if (em.getTransaction().isActive()){
                            em.getTransaction().rollback();
                        }
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    Administracao admin;
                    System.out.print("Informe o id do perfil a ser removido: ");
                    Integer id1 = scan.nextInt();
                    scan.nextLine();
                    admin = em.find(Administracao.class, id1);
                    System.out.println(admin+"\n");
                    System.out.println("Este é o perfil que deseja remover? S - Sim ou N - Não");
                    char resp1 = scan.nextLine().charAt(0);
                    while (resp1 != 'S' && resp1 != 's') {
                        System.out.println("ok!");
                        System.out.print("Informe o id do perfil a ser removido: ");
                        int cod = scan.nextInt();
                        scan.nextLine();
                        admin = em.find(Administracao.class, cod);
                        System.out.println("Ok! então este perfil será removido");
                        try {
                            em.getTransaction().begin();
                            em.remove(admin);
                            em.getTransaction().commit();
                            System.out.println("Dados deletados com sucesso!");
                        }catch (Exception e){
                            if (em.getTransaction().isActive()){
                                em.getTransaction().rollback();
                            }
                            e.printStackTrace();
                        }
                        break;
                    }
                    try {
                        em.getTransaction().begin();
                        em.remove(admin);
                        em.getTransaction().commit();
                        System.out.println("Dados deletados com sucesso!");
                    }catch (Exception e){
                        if (em.getTransaction().isActive()){
                            em.getTransaction().rollback();
                        }
                        e.printStackTrace();
                    }
                    break;
                default:
                    if(opcaoMenu  == 0){
                        System.out.println("Sair Menu");
                    } else if (opcaoMenu < 0 || opcaoMenu > 2) {
                        System.out.println("Opção invalida");
                    }
            }
        }while (opcaoMenu != 0);

        }catch (Exception e){
            System.out.println(e);
        }
    }
}




