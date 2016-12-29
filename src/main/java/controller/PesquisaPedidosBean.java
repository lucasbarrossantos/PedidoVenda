package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class PesquisaPedidosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Integer> produtosFiltrados;

    public PesquisaPedidosBean() {
        produtosFiltrados = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            produtosFiltrados.add(i);
        }
    }

    public List<Integer> getProdutosFiltrados() {
        return produtosFiltrados;
    }

}