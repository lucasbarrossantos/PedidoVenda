package repository;


import filter.UsuarioFilter;
import modelo.Grupo;
import modelo.Usuario;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import service.NegocioException;
import util.jsf.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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
        }catch (PersistenceException e){
            throw new NegocioException("Usuário não pode ser excluído, pois, há grupos vinculados!");
        }
    }

    public Usuario porId(Long id) {
        return manager.find(Usuario.class, id);
    }
}