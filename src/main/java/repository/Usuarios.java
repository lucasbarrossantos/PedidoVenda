package repository;


import repository.filter.UsuarioFilter;
import modelo.Usuario;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import service.NegocioException;
import util.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Usuario guardar(Usuario usuario) {
        return manager.merge(usuario);
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> filtrados(UsuarioFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Usuario.class);

        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotBlank(filtro.getEmail())) {
            criteria.add(Restrictions.ilike("email", filtro.getEmail(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("nome")).list();
    }

    @Transactional
    public void remover(Usuario usuario) {
        try {
            usuario = porId(usuario.getId());
            manager.remove(usuario);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Usuário não pode ser excluído, pois, há grupos vinculados!");
        }
    }

    public Usuario porId(Long id) {
        return manager.find(Usuario.class, id);
    }

    public List<Usuario> vendedores() {
        return this.manager.createQuery("from Usuario ", Usuario.class).getResultList();
    }

    public Usuario porEmail(String email) {
        Usuario usuario = null;
        try {
            usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
                    .setParameter("email", email.toLowerCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            //TODO nenhum usuário encontrado com e-mail informado
        }
        return usuario;
    }
}
