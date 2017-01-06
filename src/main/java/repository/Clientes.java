package repository;

import repository.filter.ClienteFilter;
import modelo.Cliente;
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
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;


public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public List<Cliente> todos() {
        return manager.createQuery("from Cliente ", Cliente.class).getResultList();
    }

    public Cliente guardar(Cliente cliente) {
        return manager.merge(cliente);
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> filtrados(ClienteFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Cliente.class);

        if (StringUtils.isNotBlank(filtro.getCpf()))
            criteria.add(Restrictions.eq("documentoReceitaFederal", filtro.getCpf()));

        if (StringUtils.isNotBlank(filtro.getCnpj()))
            criteria.add(Restrictions.eq("cnpj", filtro.getCnpj()));

        if (StringUtils.isNotBlank(filtro.getNome()))
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));

        return criteria.addOrder(Order.asc("nome")).list();

    }

    public List<Cliente> porNome(String nome){
        return this.manager.createQuery("from Cliente as c where c.nome like :nome", Cliente.class)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();
    }

    public Cliente porId(Long id) {
        return manager.find(Cliente.class, id);
    }

    @Transactional
    public void remover(Cliente clienteSelecionado) {
        try {
            Cliente cliente = porId(clienteSelecionado.getId());
            manager.remove(cliente);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("O cliente não pode ser removido.");
        }
    }
}
