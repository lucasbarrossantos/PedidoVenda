package service;

import modelo.Produto;
import repository.Produtos;
import util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroProdutoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Produtos produtos;

    @Transactional
    public Produto salvar(Produto produto) {
        Produto produtoExistente = produtos.porSKU(produto.getSku());

        if (produtoExistente != null && !produtoExistente.equals(produto)) {
            throw new NegocioException("Já existe um produto com o SKU informado.");
        }

        return produtos.guardar(produto);
    }

}
