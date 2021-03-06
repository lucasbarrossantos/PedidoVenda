package controller;

import repository.filter.ProdutoFilter;
import modelo.Produto;
import repository.Produtos;
import service.NegocioException;
import util.jsf.FacesUtil;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Produtos produtos;

    private Produto produtoSelecionado;

    private ProdutoFilter filtro;
    private List<Produto> produtosFiltrados;

    public PesquisaProdutosBean() {
        filtro = new ProdutoFilter();
    }

    public void excluir() throws NegocioException {
        try {
            produtos.remover(produtoSelecionado);
            produtosFiltrados.remove(produtoSelecionado);
            FacesUtil.addInfoMessage("Produto: " + produtoSelecionado.getSku() + " excluído com sucesso.");
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }

    public void pesquisar() {
        produtosFiltrados = produtos.filtrados(filtro);
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public ProdutoFilter getFiltro() {
        return filtro;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }
}