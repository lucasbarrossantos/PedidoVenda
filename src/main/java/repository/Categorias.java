package repository;

import modelo.Categoria;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class Categorias implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public List<Categoria> raizes() {
        return manager.createQuery("from Categoria as c where c.categoriaPai is null ", Categoria.class).
                getResultList();
    }

    public Categoria porId(Long id) {
        return manager.find(Categoria.class, id);
    }

    public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
        return manager.createQuery("from Categoria where categoriaPai = :raiz", Categoria.class)
                .setParameter("raiz", categoriaPai)
                .getResultList();
    }
}
