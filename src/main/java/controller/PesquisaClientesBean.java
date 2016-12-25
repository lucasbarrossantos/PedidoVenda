package controller;

import modelo.Cliente;
import modelo.TipoPessoa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean
@SessionScoped
public class PesquisaClientesBean {

    private Cliente cliente;
    private List<Cliente> clientes = new ArrayList<>();

    public PesquisaClientesBean() {
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Supermercado João das Couves Ltda");
        cliente.setCnpj("02.493.738/0001-83");
        cliente.setTipo(TipoPessoa.JURIDICA);
        clientes.add(cliente);

        Cliente cliente1 = new Cliente();
        cliente1.setId(2);
        cliente1.setNome("Maria Conceião da Abadia");
        cliente1.setCnpj("045.938.553-99");
        cliente1.setTipo(TipoPessoa.FISICA);
        clientes.add(cliente1);

        Cliente cliente3 = new Cliente();
        cliente3.setId(3);
        cliente3.setNome("Supermercado Preço Bom Ltda");
        cliente3.setCnpj("08.111.344/0001-12");
        cliente3.setTipo(TipoPessoa.JURIDICA);
        clientes.add(cliente3);
    }

    public Cliente getCliente() {
        return cliente;
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