package repository;


import modelo.Grupo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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
}
