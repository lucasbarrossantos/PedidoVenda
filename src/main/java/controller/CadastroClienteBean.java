package controller;

import modelo.Cliente;
import modelo.Endereco;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class CadastroClienteBean {

    private Cliente cliente = new Cliente();
    private List<Cliente> clientes = new ArrayList<>();
    private Endereco endereco = new Endereco();
    private List<Endereco> enderecos = new ArrayList<>();

    public CadastroClienteBean() {
        endereco.setId(1);
        endereco.setCep("56328900");
        endereco.setCidade("Petrolina");
        endereco.setComplemento("Casa");
        endereco.setLogradouro("Rua Cabrobó");
        endereco.setNumero(419);
        endereco.setUf("PE");
        enderecos.add(endereco);

        Endereco endereco1 = new Endereco();
        endereco1.setId(2);
        endereco1.setCep("56328300");
        endereco1.setCidade("Orocó");
        endereco1.setComplemento("Casa");
        endereco1.setLogradouro("Av. Joaquim Amando Agra");
        endereco1.setNumero(101);
        endereco1.setUf("PE");
        enderecos.add(endereco1);

        cliente.setId(1);
        cliente.setNome("Lucas Barros Santos");
        cliente.setEnderecos(enderecos);
    }

    public void incluirEndereco(){
        enderecos.add(endereco);
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}