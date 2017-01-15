package service;

import modelo.Grupo;
import repository.Grupos;
import util.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;

public class CadastroGrupoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @Inject
    private Grupos grupos;

    @Transactional
    public Grupo adicionar(Grupo grupo) throws NegocioException {

        Grupo clone = grupos.porNome(grupo.getNome());

        if (clone != null)
            throw new NegocioException("O grupo informado já existe");

        return grupos.guardar(grupo);
    }
}
