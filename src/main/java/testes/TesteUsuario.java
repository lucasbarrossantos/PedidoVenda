package testes;


import modelo.Grupo;
import modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TesteUsuario {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedido-vendaPU");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction trx = manager.getTransaction();
        trx.begin();


        Grupo grupo = new Grupo();
        grupo.setNome("Vendas");
        grupo.setDescricao("Gerente e supervisor");


        manager.persist(grupo);

        trx.commit();
    }
}
