package controller;

import modelo.Produto;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@SessionScoped
public class FotoProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Produto produtoSelecionado;

    public StreamedContent getFoto() {
        DefaultStreamedContent content = null;
        if (this.produtoSelecionado != null && this.produtoSelecionado.getFoto() != null
                && this.produtoSelecionado.getFoto().length > 0) {
            byte[] imagem = this.produtoSelecionado.getFoto();
            content = new DefaultStreamedContent(new ByteArrayInputStream(imagem), "image/jpg", "produto.jpg");
        }
        return content;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }
}
