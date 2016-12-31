package controller;

import modelo.Categoria;
import modelo.Produto;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    @Inject
    private Produto produto;
    private List<Produto> produtos = new ArrayList<>();
    private List<Categoria> categoriasRaizes;

    public CadastroProdutoBean() {

    }

    public void inicializar() {

        categoriasRaizes = manager.createQuery("from Categoria as c where c.categoriaPai is null ", Categoria.class).
                getResultList();

    }

    public void salvar() {
        produtos.add(produto);
        System.out.println("Sanvando produto: " + produto.toString());
        produtos.forEach(p -> System.out.println("Produto: " + p.getNome() + "\n" + "SKU: " + p.getSku()));
        produtos.clear();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Categoria> getCategoriasRaizes() {
        return categoriasRaizes;
    }

}