package controller;

import modelo.Produto;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Produto produto;
    private List<Produto> produtos = new ArrayList<>();

    public void salvar(){
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
}