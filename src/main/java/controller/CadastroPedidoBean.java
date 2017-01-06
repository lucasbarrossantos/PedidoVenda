package controller;

import modelo.*;
import repository.Clientes;
import repository.Usuarios;
import service.CadastroPedidoService;
import util.jsf.FacesUtil;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    @Inject
    private Clientes clientes;

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    private Pedido pedido;
    private List<Usuario> vendedores;

    public CadastroPedidoBean() {
        limpar();
    }

    @PostConstruct
    public void init() {
        vendedores = usuarios.vendedores();
    }

    /**
     * Métodos
     */

    public void salvar() {
        this.pedido = cadastroPedidoService.salvar(pedido);
        FacesUtil.addInfoMessage("Pedido salvo com sucesso.");
    }

    private void limpar() {
        pedido = new Pedido();
        pedido.setEnderecoEntrega(new EnderecoEntrega());
    }

    public void inicializar() {
        if (FacesUtil.isPostback()) {
            this.vendedores = usuarios.vendedores();
        }
    }

    public boolean isEditando() {
        if (pedido == null)
            pedido = new Pedido();

        return this.pedido.getId() != null;
    }

    public FormaPagamento[] getFormasPagamento() {
        return FormaPagamento.values();
    }

    public List<Cliente> completarCliente(String nomeCliente) {
        return clientes.porNome(nomeCliente);
    }

    /**
     * gets and sets
     *
     * @return
     */

    public List<Usuario> getVendedores() {
        return vendedores;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}