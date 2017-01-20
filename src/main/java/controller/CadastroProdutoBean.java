package controller;

import modelo.Categoria;
import modelo.Produto;
import org.primefaces.model.UploadedFile;
import repository.Categorias;
import service.CadastroProdutoService;
import service.NegocioException;
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

    private UploadedFile fotoProduto;

    private Produto produto;
    private Categoria categoriaPai;

    private List<Produto> produtos = new ArrayList<>();
    private List<Categoria> categoriasRaizes;
    private List<Categoria> subcategorias;

    public CadastroProdutoBean() {
        produto = new Produto();
        limpar();
    }

    public boolean isEditando() {
        return this.produto.getId() != null;
    }

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            if (produto == null) {
                produto = new Produto();
            }
            categoriasRaizes = categorias.raizes();
        }

        if (this.categoriaPai != null)
            carregarSubcategorias();
    }

    public void salvar() throws NegocioException {
        try {
            //Verifica se tem imagem e atribui
            if (this.fotoProduto != null && this.fotoProduto.getContents() != null
                    && this.fotoProduto.getContents().length > 0) {
                this.produto.setFoto(this.fotoProduto.getContents());
            }

            this.produto = cadastroProdutoService.salvar(this.produto);
            limpar();
            FacesUtil.addInfoMessage("Produto salvo com sucesso!");
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }

    private void limpar() {
        produto = new Produto();
        categoriaPai = null;
        subcategorias = new ArrayList<>();
    }

    public void carregarSubcategorias() {
        subcategorias = categorias.subcategoriasDe(categoriaPai);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        if (produto != null) {
            this.categoriaPai = produto.getCategoria().getCategoriaPai();
        }
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

    public UploadedFile getFotoProduto() {
        return fotoProduto;
    }

    public void setFotoProduto(UploadedFile fotoProduto) {
        this.fotoProduto = fotoProduto;
    }
}