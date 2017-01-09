package service;


import modelo.Pedido;
import modelo.StatusPedido;
import repository.Pedidos;
import util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;

public class EmissaoPedidoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    @Inject
    private EstoqueService estoqueService;

    @Inject
    private Pedidos pedidos;

    @Transactional
    public Pedido emitir(Pedido pedido) {
        pedido = this.cadastroPedidoService.salvar(pedido);

        if (pedido.isNaoEmissivel()) {
            throw new NegocioException("Pedido não pode ser emitido com status " + pedido.getStatus().getDescricao() + ".");
        }

        this.estoqueService.baixarItensEstoque(pedido);

        pedido.setStatus(StatusPedido.EMITIDO);
        pedido = this.pedidos.guardar(pedido);
        return pedido;
    }
}
