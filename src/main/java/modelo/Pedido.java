package modelo;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido extends EntidadeBase {

    @Past
    @NotNull(message = "deve ser informado")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao", nullable = false)
    private Date dataCriacao;

    @Column(columnDefinition = "text")
    private String observacao;

    @NotNull(message = "deve ser informado")
    @Column(name = "data_entrega", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;

    @NotNull(message = "deve ser informado")
    @Column(name = "valor_frete", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorFrete = BigDecimal.ZERO;

    @NotNull(message = "deve ser informado")
    @Column(name = "valor_desconto", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorDesconto = BigDecimal.ZERO;

    @NotNull(message = "deve ser informado")
    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @NotNull
    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.ORCAMENTO;

    @NotNull
    @Column(nullable = false, length = 20, name = "forma_pagamento")
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Usuario vendedor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Embedded
    private EnderecoEntrega enderecoEntrega;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemPedido> itens = new ArrayList<>();

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EnderecoEntrega getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Transactional
    public boolean isExistente() {
        return isPersisted();
    }

    @PrePersist
    void prePersist() {
        setDataCriacao(new Date());
    }

    @Transient
    public BigDecimal getValorSubtotal() {
        return this.getValorTotal().subtract(this.getValorFrete()).add(this.getValorDesconto());
    }

    public void recalcularValorTotal() {
        BigDecimal total = BigDecimal.ZERO;
        total = total.add(this.getValorFrete()).subtract(this.getValorDesconto());

        for (ItemPedido item : this.getItens()) {
            if (item.getProduto() != null && item.getProduto().getId() != null) {
                total = total.add(item.getValorTotal());
            }
        }

        this.setValorTotal(total);
    }

    public void adicionarItemVazio() {
        if (this.isOrcamento()) {
            Produto produto = new Produto();

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setPedido(this);

            this.getItens().add(0, item);
        }
    }

    @Transient
    public boolean isOrcamento() {
        return StatusPedido.ORCAMENTO.equals(this.getStatus());
    }

    public void removerItemVazio() {
        ItemPedido primeiroItem = this.getItens().get(0);

        if (primeiroItem != null && primeiroItem.getProduto().getId() == null) {
            this.getItens().remove(0);
        }
    }

    @Transient
    public boolean isValorTotalNegativo() {
        return this.getValorTotal().compareTo(BigDecimal.ZERO) < 0;
    }

    @Transient
    public boolean isEmitido() {
        return StatusPedido.EMITIDO.equals(this.getStatus());
    }

    @Transient
    public boolean isNaoEmissivel() {
        return !this.isEmissivel();
    }

    @Transient
    public boolean isEmissivel() {
        return this.isExistente() && this.isOrcamento();
    }

    @Transient
    public boolean isNaoCancelavel() {
        return !this.isCancelavel();
    }

    @Transient
    public boolean isCancelavel() {
        return this.isExistente() && !this.isCancelado();
    }

    @Transient
    public boolean isCancelado() {
        return StatusPedido.CANCELADO.equals(this.getStatus());
    }

    @Transient
    public boolean isSemEnderecoEntrega() {
        return getDataEntrega() == null
                || getEnderecoEntrega().getCep() == null
                || getEnderecoEntrega().getCidade() == null
                || getEnderecoEntrega().getLogradouro() == null
                || getEnderecoEntrega().getNumero() == null
                || getEnderecoEntrega().getUf() == null;
    }

    @Transient
    public boolean isNaoAlteravel() {
        return !this.isAlteravel();
    }

    @Transient
    public boolean isAlteravel() {
        return this.isOrcamento();
    }

    @Transient
    public boolean isEnderecoEntregaEditavel(){
        return this.getStatus().equals(StatusPedido.CANCELADO);
    }
}
