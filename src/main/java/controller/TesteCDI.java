package controller;

import modelo.Cliente;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("meuBeans")
@RequestScoped
public class TesteCDI implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private Cliente cliente;
    private List<Cliente> clientes = new ArrayList<>();

    public TesteCDI() {

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void adicionar() {
        clientes.add(cliente);
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
    }

    @PostConstruct
    public void init(){
        System.out.println("Init Teste CDI");
        System.out.println("Classe: " + cliente.getClass());
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
