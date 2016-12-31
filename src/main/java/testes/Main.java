package testes;


import modelo.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedido-vendaPU");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction trx = manager.getTransaction();
        trx.begin();

        Categoria categoria = new Categoria();
        categoria.setDescricao("Acessórios");

        manager.persist(categoria);

        trx.commit();

        manager.close();
        factory.close();

        System.out.println("\n Conectado! \n");


    }
}
