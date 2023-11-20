package com.apresentacao.aplication;


import com.apresentacao.Administracao;
import com.apresentacao.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicajpa");
        System.out.println("Hello");
        //Administracao adm = new Administracao();
        Administracao adm1 = new Administracao(null,"MEDICO","Medico");
        //Administracao adm2 = new Administracao(null,"Paciente","Paciente");
        //Administracao adm3 = new Administracao(null,"Administrador","Administrador");
        //Administracao adm5 = new Administracao(null, "Assistente", "Administrador Assistente");
        //System.out.println(adm5);

        Administracao adm;
        Usuario p = null;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        p = em.find(Usuario.class, 2);
        System.out.println(p);
        em.getTransaction().commit();

        /*
        em.getTransaction().begin();
        em.persist(adm1);
        em.persist(adm2);
        em.persist(adm3);
        System.out.println("Atualizada a tabela administração");
        em.getTransaction().commit();*/
        //p = em.find(Paciente.class,2);


        //System.out.println(p);
        /*
        p.setNome("Valeria Cardoso");
        p.setEmail("valcardoso@agmail.com");
        p.setEndereco("Av.Paulista, 10035, São Paulo/SP");
        p.setTelefone("9991-0009");
        em.merge(p);
        /*
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        */
        /*
        em.remove(p);

        em.getTransaction().commit();
        em.close();
            */
        //p = em.find(Paciente.class,2);
        //System.out.println(p);
       /* EntityManager em1 = emf.createEntityManager();
        em1.getTransaction().begin();
        em1.persist(adm5);
        em1.getTransaction().commit();
        em1.close();
        */

        emf.close();
    }
}
