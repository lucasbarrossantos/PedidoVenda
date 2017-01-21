package util.jpa;

import org.hibernate.Session;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

    private EntityManagerFactory factory;

    public EntityManagerProducer() {
        factory = Persistence.createEntityManagerFactory("pedido-vendaPU");
    }

    @Produces
    @RequestScoped
    public Session createEntityManager() {
        return (Session) factory.createEntityManager();
    }

    /**
     * Funcionará como um gatílio.
     * Quando a requisição terminar, então, o método é chamado e o manager é fechado!
     * @param manager
     */
    public void closeEntityManager(@Disposes Session manager) {
        manager.close();
    }
}
