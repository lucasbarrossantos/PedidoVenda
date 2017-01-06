package controller;

import modelo.Cliente;
import modelo.Endereco;
import modelo.TipoPessoa;
import org.primefaces.context.RequestContext;
import service.CadastroClienteService;
import util.jsf.FacesUtil;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroClienteService cadastroClienteService;

    private Cliente cliente;

    private Endereco enderecoRascunho;
    private List<Endereco> enderecosRascunho = new ArrayList<>();

    private Endereco enderecoSelecionado;

    public CadastroClienteBean() {
        cliente = new Cliente();
        enderecoRascunho = new Endereco();
        cliente.setEnderecos(new ArrayList<>());
    }

    /**
     * Métodos
     */

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            if (cliente == null)
                cliente = new Cliente();

            enderecosRascunho = cliente.getEnderecos();
        }
    }

    public void prepararEndereco() {
        this.enderecoRascunho = new Endereco();
    }

    public void salvar() {
        enderecosRascunho.forEach(e -> e.setCliente(cliente)); // Adicionando o clientes aos endereços
        cliente.setEnderecos(enderecosRascunho); // Adicionando os endereços ao cliente
        cliente = cadastroClienteService.salvar(cliente);
        limpar();
        FacesUtil.addInfoMessage("Cliente salvo com sucesso.");
    }

    private void limpar() {
        cliente = new Cliente();
        enderecosRascunho = new ArrayList<>();
        enderecosRascunho.clear();
        RequestContext.getCurrentInstance().update(Arrays.asList("frm:enderecos-clientes-table"));
    }

    public void excluirEnderecoSelecionado() {
        enderecosRascunho.remove(enderecoSelecionado);
    }

    public void incluirEndereco() {
        if (!cliente.isPersisted())
            enderecosRascunho.add(enderecoRascunho);
        else cadastroClienteService.salvar(cliente); // Faz o um merge do cliente
    }

    public void prepararEnderecoEdicao() {
        enderecoRascunho = enderecoSelecionado;
    }

    public boolean editando(){
        return this.cliente.getId() != null;
    }

    /**
     * geters and sets
     *
     * @return
     */

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoRascunho() {
        return enderecoRascunho;
    }

    public void setEnderecoRascunho(Endereco enderecoRascunho) {
        this.enderecoRascunho = enderecoRascunho;
    }

    public Endereco getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }

    public TipoPessoa[] getTipos() {
        return TipoPessoa.values();
    }

    public List<Endereco> getEnderecosRascunho() {
        return enderecosRascunho;
    }
}