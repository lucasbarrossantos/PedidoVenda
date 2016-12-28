package testes;


import modelo.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedido-vendaPU");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction trx = manager.getTransaction();
        trx.begin();

        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setEmail("maria@abadia.com");
        usuario.setSenha("123");

        Grupo grupo = new Grupo();
        grupo.setNome("Vendedores");
        grupo.setDescricao("Vendedores da empresa");

        Grupo grupo1 = new Grupo();
        grupo1.setNome("Auxiliares");
        grupo1.setDescricao("Auxiliares de vendas");

        usuario.setGrupos(Arrays.asList(grupo, grupo1));

        manager.persist(usuario);

        trx.commit();

        manager.close();
        factory.close();

        System.out.println("\n Conectado! \n");


    }
}
