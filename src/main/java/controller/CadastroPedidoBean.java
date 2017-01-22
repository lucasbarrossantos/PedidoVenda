package controller;

import modelo.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import repository.Clientes;
import repository.Produtos;
import repository.Usuarios;
import service.CadastroPedidoService;
import service.NegocioException;
import util.jsf.FacesUtil;
import validation.SKU;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    @Inject
    private Clientes clientes;

    @Inject
    private Produtos produtos;

    @Inject
    private CadastroPedidoService cadastroPedidoService;

    @SKU
    private String sku;

    @Produces
    @PedidoEdicao
    private Pedido pedido;
    private List<Usuario> vendedores;
    private Produto produtoLinhaEditavel;

    public CadastroPedidoBean() {
        limpar();
    }

    @PostConstruct
    public void init() {
        vendedores = usuarios.vendedores();
        pedido.setEnderecoEntrega(new EnderecoEntrega());
    }

    /**
     * Métodos
     */

    public void salvar() throws NegocioException {
        try {
            if (this.pedido.isSemEnderecoEntrega()) {
                throw new NegocioException("Informe um endereço de entrega para o pedido!");
            } else {
                try {
                    this.pedido.removerItemVazio();
                    this.pedido = cadastroPedidoService.salvar(pedido);
                    FacesUtil.addInfoMessage("Pedido salvo com sucesso.");
                } finally {
                    this.pedido.adicionarItemVazio();
                }
            }
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }

    public void clienteSelecionado(SelectEvent event) {
        pedido.setCliente((Cliente) event.getObject()); // Passa o cliente selecionado
        RequestContext.getCurrentInstance().update("frm:cliente");
    }

    private void limpar() {
        pedido = new Pedido();
        pedido.setEnderecoEntrega(new EnderecoEntrega());
    }

    public void carregarDadosEntrega() {
        if (this.pedido.getCliente().getEnderecos().size() > 0) {
            this.pedido.carregarEnderecoRascunho();
        } else {
            this.pedido.setEnderecoEntrega(new EnderecoEntrega());
        }
    }

    /**
     * Observador do pedido alterado event
     *
     * @param event
     */
    public void pedidoAlterado(@Observes PedidoAlteradoEvent event) {
        this.pedido = event.getPedido();
    }

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            this.vendedores = usuarios.vendedores();

            if (this.pedido == null) {
                pedido = new Pedido();
                pedido.setEnderecoEntrega(new EnderecoEntrega());
            }

            this.pedido.adicionarItemVazio();

            this.recalcularPedido();
        }
    }

    public boolean isEditando() {
        if (pedido == null)
            pedido = new Pedido();

        return this.pedido.getId() != null;
    }

    public FormaPagamento[] getFormasPagamento() {
        return FormaPagamento.values();
    }

    public List<Cliente> completarCliente(String nomeCliente) {
        return clientes.porNome(nomeCliente);
    }

    public void recalcularPedido() {
        if (this.pedido != null) {
            this.pedido.recalcularValorTotal();
        }
    }

    public List<Produto> completarProduto(String nomeProduto) {
        return produtos.porNome(nomeProduto);
    }

    public void carregarProdutoLinhaEditavel() {
        ItemPedido item = this.pedido.getItens().get(0);

        if (this.produtoLinhaEditavel != null) {

            if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
                FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
            } else {
                item.setProduto(this.produtoLinhaEditavel);
                item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());

                this.pedido.adicionarItemVazio();
                this.produtoLinhaEditavel = null;
                this.sku = null;

                this.pedido.recalcularValorTotal();
            }
        }
    }

    private boolean existeItemComProduto(Produto produto) {
        boolean existeItem = false;

        for (ItemPedido item : this.getPedido().getItens()) {
            if (produto.equals(item.getProduto())) {
                existeItem = true;
                break;
            }
        }

        return existeItem;
    }

    public void carregarProdutoPorSku() {
        if (StringUtils.isNotEmpty(this.sku)) {
            this.produtoLinhaEditavel = produtos.porSKU(this.sku);
            this.carregarProdutoLinhaEditavel();
        }
    }

    public void atualizarQuantidade(ItemPedido item, int linha) {

        if (item.getQuantidade() < 1) {
            if (linha == 0) {
                item.setQuantidade(1);
            } else {
                this.getPedido().getItens().remove(linha);
            }
        }

        this.pedido.recalcularValorTotal();
    }

    /**
     * gets and sets
     *
     * @return
     */

    public List<Usuario> getVendedores() {
        return vendedores;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProdutoLinhaEditavel() {
        return produtoLinhaEditavel;
    }

    public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
        this.produtoLinhaEditavel = produtoLinhaEditavel;
    }

    @SKU
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Date getDateHoje() {
        return new Date();
    }

    @NotBlank(message = "deve ser informado")
    public String getNomeCliente() {
        return pedido.getCliente() == null ? null : pedido.getCliente().getNome();
    }

    /**
     * Para driblar o erro do campo readonly do campo nome do cliente
     *
     * @param nome
     */
    public void setNomeCliente(String nome) {
    }
}