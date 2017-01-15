package repository;


import modelo.Grupo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;

public class Grupos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public List<Grupo> todos() {
        return manager.createQuery("from Grupo ", Grupo.class).getResultList();
    }

    public Object porId(Long id) {
        return manager.find(Grupo.class, id);
    }

    public Grupo guardar(Grupo grupo) {
        return this.manager.merge(grupo);
    }

    public Grupo porNome(String nome) {
        try {
            return manager.createQuery("from Grupo where upper(nome) = :nome", Grupo.class)
                    .setParameter("nome", nome.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
        }
        return null;
    }
}
