package modelo;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "endereco")
public class Endereco extends EntidadeBase {

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String logradouro;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String numero;

    @Size(max = 150)
    @Column(length = 150)
    private String complemento;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String cidade;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String uf;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 9)
    private String cep;

    @NotBlank
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String bairro;

    @NotNull
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
}
