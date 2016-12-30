package controller;

import modelo.Cliente;
import modelo.Endereco;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Cliente cliente;

    private Endereco endereco;

    private Endereco enderecoSelecionado;

    private List<Cliente> clientes = new ArrayList<>();

    public CadastroClienteBean() {
        cliente = new Cliente();
        endereco = new Endereco();
        cliente.setEnderecos(new ArrayList<>());
    }

    @PostConstruct
    public void init() {

    }

    public void prepararEndereco(){
        this.endereco = new Endereco();
    }

    public void salvar() {
        System.out.println("Salvando cliente: " + cliente.toString());
    }

    public void excluirEnderecoSelecionado() {
        cliente.getEnderecos().remove(enderecoSelecionado);
    }

    public void incluirEndereco() {
        cliente.getEnderecos().add(endereco);
        System.out.println("Excluir endereco");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }
}