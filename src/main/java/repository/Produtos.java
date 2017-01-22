package repository;


import modelo.Categoria;
import org.apache.commons.lang3.StringUtils;
import repository.filter.ProdutoFilter;
import modelo.Produto;
import service.NegocioException;
import util.jpa.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Produto guardar(Produto produto) {
        return manager.merge(produto);
    }

    @Transactional
    public void remover(Produto produto) throws NegocioException {
        try {
            produto = porId(produto.getId());
            manager.remove(produto);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Produto não pode ser excluído.");
        }
    }

    public Produto porSKU(String sku) {
        try {
            return manager.createQuery("from Produto where upper(sku) = :sku", Produto.class)
                    .setParameter("sku", sku.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public List<Produto> filtrados(ProdutoFilter filtro) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = builder.createQuery(Produto.class);
        List<Predicate> predicates = new ArrayList<>(); // Para adicionar as restrições

        Root<Produto> produtoRoot = criteriaQuery.from(Produto.class);
        Fetch<Produto, Categoria> categoriaJoin = produtoRoot.fetch("categoria", JoinType.INNER);
        categoriaJoin.fetch("categoriaPai", JoinType.INNER);


        if (StringUtils.isNotBlank(filtro.getSku())) {
            //criteria.add(Restrictions.eq("sku", filtro.getSku()));
            predicates.add(builder.equal(produtoRoot.get("sku"), filtro.getSku()));
        }

        if (StringUtils.isNotBlank(filtro.getNome())) {
            predicates.add(builder.like(builder.lower(produtoRoot.get("nome")),
                    "%" + filtro.getNome().toLowerCase() + "%"));
        }

        criteriaQuery.select(produtoRoot);
        criteriaQuery.where(predicates.toArray(new Predicate[0])); //Instancia do tipo que tem que retornar
        criteriaQuery.orderBy(builder.asc(produtoRoot.get("nome")));

        TypedQuery<Produto> query = manager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Produto porId(Long id) {
        return manager.find(Produto.class, id);
    }

    public List<Produto> porNome(String nomeProduto) {
        return this.manager.createNamedQuery("Produto.porNome", Produto.class)
                .setParameter("nome", "%" + nomeProduto.toUpperCase() + "%")
                .getResultList();
    }
}
