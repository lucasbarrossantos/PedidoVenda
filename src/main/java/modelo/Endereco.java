package modelo;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "endereco")
public class Endereco extends EntidadeBase {

    @NotBlank(message = "deve ser informado")
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String logradouro;

    @NotBlank(message = "deve ser informado")
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String numero;

    @Size(max = 150)
    @Column(length = 150)
    private String complemento;

    @NotBlank(message = "deve ser informado")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String cidade;

    @NotBlank(message = "deve ser informado")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String uf;

    @NotBlank(message = "deve ser informado")
    @Size(max = 60)
    @Column(nullable = false, length = 9)
    private String cep;

    @NotBlank(message = "deve ser informado")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String bairro;

    @NotNull(message = "deve ser informado")
    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente cliente;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        if (!super.equals(o)) return false;

        Endereco endereco = (Endereco) o;

        if (!getLogradouro().equals(endereco.getLogradouro())) return false;
        if (!getNumero().equals(endereco.getNumero())) return false;
        if (!getComplemento().equals(endereco.getComplemento())) return false;
        if (!getCidade().equals(endereco.getCidade())) return false;
        if (!getUf().equals(endereco.getUf())) return false;
        if (!getCep().equals(endereco.getCep())) return false;
        if (!getBairro().equals(endereco.getBairro())) return false;
        return getCliente().equals(endereco.getCliente());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getLogradouro().hashCode();
        result = 31 * result + getNumero().hashCode();
        result = 31 * result + getComplemento().hashCode();
        result = 31 * result + getCidade().hashCode();
        result = 31 * result + getUf().hashCode();
        result = 31 * result + getCep().hashCode();
        result = 31 * result + getBairro().hashCode();
        result = 31 * result + getCliente().hashCode();
        return result;
    }
}
