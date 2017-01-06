package controller;

import modelo.Pedido;
import modelo.StatusPedido;
import repository.Pedidos;
import repository.filter.PedidoFilter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Pedidos pedidos;

    private PedidoFilter filtro;
    private List<Pedido> pedidosFiltrados;

    public PesquisaPedidosBean() {
        filtro = new PedidoFilter();
        pedidosFiltrados = new ArrayList<>();
    }

    /**
     * TODO métodos
     */

    public void pesquisar() {
        pedidosFiltrados = pedidos.filtrados(filtro);
    }

    /**
     * geters and sets
     *
     * @return
     */

    public List<Pedido> getPedidosFiltrados() {
        return pedidosFiltrados;
    }

    public PedidoFilter getFiltro() {
        return filtro;
    }

    public StatusPedido[] getStatusPedidos() {
        return StatusPedido.values();
    }
}