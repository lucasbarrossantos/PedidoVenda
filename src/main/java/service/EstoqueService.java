package service;

import modelo.ItemPedido;
import modelo.Pedido;
import repository.Pedidos;
import util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;

public class EstoqueService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Pedidos pedidos;

    @Transactional
    public void baixarItensEstoque(Pedido pedido){
        pedido = this.pedidos.porId(pedido.getId());

        for (ItemPedido item : pedido.getItens()){
            item.getProduto().baixarEstoque(item.getQuantidade());
        }
    }

    public void retornarItensEstoque(Pedido pedido) {
        pedido = this.pedidos.porId(pedido.getId());

        for (ItemPedido item : pedido.getItens()){
            item.getProduto().adicionarEstoque(item.getQuantidade());
        }
    }
}
