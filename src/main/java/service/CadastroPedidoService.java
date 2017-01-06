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
    public Pedido salvar(Pedido pedido){
        return pedidos.guardar(pedido);
    }
}
