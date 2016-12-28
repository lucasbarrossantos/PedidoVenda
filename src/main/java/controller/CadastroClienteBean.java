package controller;

import modelo.Cliente;
import modelo.Endereco;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Cliente cliente;

    @Inject
    private Endereco endereco;

    private List<Cliente> clientes = new ArrayList<>();
    private List<Endereco> enderecos = new ArrayList<>();

    public CadastroClienteBean() {

    }

    @PostConstruct
    public void init(){

        endereco.setId(1L);
        endereco.setCep("56328900");
        endereco.setCidade("Petrolina");
        endereco.setComplemento("Casa");
        endereco.setLogradouro("Rua Cabrobó");
        endereco.setNumero("419");
        endereco.setUf("PE");
        enderecos.add(endereco);

        Endereco endereco1 = new Endereco();
        endereco1.setId(2L);
        endereco1.setCep("56328300");
        endereco1.setCidade("Orocó");
        endereco1.setComplemento("Casa");
        endereco1.setLogradouro("Av. Joaquim Amando Agra");
        endereco1.setNumero("101");
        endereco1.setUf("PE");
        enderecos.add(endereco1);

        cliente.setId(1L);
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