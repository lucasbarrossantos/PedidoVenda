package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean
@SessionScoped
public class PesquisaClientesBean {

    private List<Integer> produtosFiltrados;
    private String[] nomes = {"Supermercado João das Couves Ltda", "Maria Conceição da Abadia", "Supermercado Preço Bom Ltda"};

    private List<String> pessoas = new ArrayList<>();

    public PesquisaClientesBean() {
        produtosFiltrados = new ArrayList<>();
        pessoas.addAll(Arrays.asList(nomes).subList(0, 3));
    }

    public List<Integer> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public List<String> getPessoas() {
        return pessoas;
    }
}