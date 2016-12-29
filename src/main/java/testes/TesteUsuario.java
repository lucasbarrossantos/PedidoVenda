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

        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setEmail("maria@abadia.com");
        usuario.setSenha("123");

        Grupo grupo = new Grupo();
        grupo.setNome("Vendedores");
        grupo.setDescricao("Vendedores da empresa");

        usuario.getGrupos().add(grupo);

        manager.persist(usuario);

        trx.commit();
    }
}
