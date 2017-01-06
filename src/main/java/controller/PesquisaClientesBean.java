package controller;

import repository.filter.ClienteFilter;
import modelo.Cliente;
import repository.Clientes;
import util.jsf.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Clientes clientes;

    private Cliente cliente;
    private Cliente clienteSelecionado;

    private ClienteFilter filtro;
    private List<Cliente> clientesFiltrados;

    public PesquisaClientesBean() {
        cliente = new Cliente();
        filtro = new ClienteFilter();
    }

    /**
     * TODO métodos
     */

    public void pesquisar() {
        if (filtro.getPesquisandoPor().length() == 14)
            filtro.setCpf(filtro.getPesquisandoPor());
        else if (filtro.getPesquisandoPor().length() == 18)
            filtro.setCnpj(filtro.getPesquisandoPor());
        else {
            filtro.setCnpj("");
            filtro.setCpf("");
        }

        clientesFiltrados = clientes.filtrados(filtro);
    }

    public void remover() {
        clientes.remover(clienteSelecionado);
        clientesFiltrados.remove(clienteSelecionado);
        FacesUtil.addInfoMessage("Cliente removido com sucesso.");
    }

    /**
     * TODO geters and sets
     *
     * @return
     */

    public ClienteFilter getFiltro() {
        return filtro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }
}