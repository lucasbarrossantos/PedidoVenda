package service;

import modelo.Pedido;
import repository.Pedidos;
import util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroPedidoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Pedidos pedidos;

    @Transactional
    public Pedido salvar(Pedido pedido) throws NegocioException {
        pedido.recalcularValorTotal();

        if (pedido.getItens().isEmpty()){
            throw new NegocioException("O pedido deve possuir pelo menos um item.");
        }

        if (pedido.isEnderecoEntregaEditavel()){
            throw new NegocioException("Pedido não pode ser alterado no status " + pedido.getStatus().getDescricao() + ".");
        }

        if (pedido.isValorTotalNegativo()){
            throw new NegocioException("Valor total do pedido não pode ser negativo.");
        }

        if (pedido.isSemEnderecoEntrega()){
            throw new NegocioException("Informe um endereço de entrega para o produto!");
        }

        return pedidos.guardar(pedido);
    }
}
