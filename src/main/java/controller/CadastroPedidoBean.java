package controller;

import service.NegocioException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CadastroPedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Integer> itens;

    public CadastroPedidoBean() {
        itens = new ArrayList<>();
        itens.add(1);
    }

    public List<Integer> getItens() {
        return itens;
    }

    public void salvar(){
        throw new NegocioException("Pedido não pode ser salvar, pois ainda não foi implementado.");
    }

}