package controller;

import service.NegocioException;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CadastroPedidoBean {

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