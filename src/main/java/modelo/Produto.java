package modelo;


import org.hibernate.validator.constraints.NotBlank;
import validation.SKU;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto extends EntidadeBase {

    @Column(nullable = false, length = 80)
    @NotBlank(message = "deve ser informado")
    @Size(max = 80)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "deve ser informado")
    @SKU
    private String sku;

    @NotNull(message = "deve ser informado")
    @Min(value = 1, message = "valor mínimo é 1")
    @Column(precision = 10, scale = 2, name = "valor_unitario")
    private BigDecimal valorUnitario;

    @NotNull(message = "deve ser informado")
    @Min(value = 1, message = "valor mínimo é 1")
    @Max(value = 9999, message = "tem um valor muito alto")
    @Column(precision = 10, scale = 2, name = "quantidade_estoque", length = 5, nullable = false)
    private Integer quantidadeEstoque;

    @NotNull(message = "deve ser informado")
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.toUpperCase();
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", sku='" + sku + '\'' +
                ", valorUnitario=" + valorUnitario +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", categoria=" + categoria +
                '}';
    }
}
