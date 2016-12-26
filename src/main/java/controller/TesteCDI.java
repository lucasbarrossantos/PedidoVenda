package controller;

import modelo.Cliente;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("meuBeans")
public class TesteCDI implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private Cliente cliente;

    public TesteCDI() {

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void CDI() {
        cliente.setNome("Lukinhas");
        System.out.println("Nome: " + cliente.getNome());
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
