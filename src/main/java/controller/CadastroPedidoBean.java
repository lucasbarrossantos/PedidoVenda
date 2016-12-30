package controller;

import modelo.EnderecoEntrega;
import modelo.Pedido;
import service.NegocioException;

import java.io.Serializable;
import java.util.ArrayList;
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
    private Pedido pedido;

    private List<Integer> itens;

    public CadastroPedidoBean() {
        itens = new ArrayList<>();
        itens.add(1);
    }

    @PostConstruct
    public void init(){
        pedido.setEnderecoEntrega(new EnderecoEntrega());
    }

    public List<Integer> getItens() {
        return itens;
    }

    public void salvar() {
        System.out.println("Está salvando");
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}