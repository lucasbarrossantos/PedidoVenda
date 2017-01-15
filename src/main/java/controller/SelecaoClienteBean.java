package controller;

import modelo.Cliente;
import org.primefaces.context.RequestContext;
import repository.Clientes;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class SelecaoClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Clientes clientes;

    private String nome;

    private List<Cliente> clientesFiltrados;

    public void pesquisar() {
        clientesFiltrados = clientes.porNome(nome);
    }

    public void selecionar(Cliente cliente) {
        RequestContext.getCurrentInstance().closeDialog(cliente); // Objeto que quer devolver
    }

    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 470);
        opcoes.put("closeOnEscape", true);
        opcoes.put("responsive", true);

        RequestContext.getCurrentInstance().openDialog("/dialogos/SelecaoCliente", opcoes, null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

}