package controller;

import modelo.Categoria;
import modelo.Produto;
import repository.Categorias;
import service.CadastroProdutoService;
import util.jsf.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Categorias categorias;

    @Inject
    private CadastroProdutoService cadastroProdutoService;


    private Produto produto;
    private Categoria categoriaPai;

    private List<Produto> produtos = new ArrayList<>();
    private List<Categoria> categoriasRaizes;
    private List<Categoria> subcategorias;

    public CadastroProdutoBean() {
        produto = new Produto();
        limpar();
    }

    public void inicializar() {
        if (FacesUtil.isNotPostback())
        categoriasRaizes = categorias.raizes();
    }

    public void salvar() {
        this.produto = cadastroProdutoService.salvar(this.produto);
        limpar();
        FacesUtil.addSussesMessage("Produto salvo com sucesso!");
    }

    private void limpar(){
        produto = new Produto();
        categoriaPai = null;
        subcategorias = new ArrayList<>();
    }

    public void carregarSubcategorias(){
        subcategorias = categorias.subcategoriasDe(categoriaPai);
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

    @NotNull
    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }
}