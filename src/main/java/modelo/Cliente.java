package modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends EntidadeBase {

    @Column(length = 60, nullable = false)
    private String nome;
    @Column(name = "e_mail", nullable = false)
    private String email;
    @Column(name = "documento_receita_federal", length = 14, nullable = false, unique = true)
    private String documentoReceitaFederal;
    @Column(length = 18, unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TipoPessoa tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentoReceitaFederal() {
        return documentoReceitaFederal;
    }

    public void setDocumentoReceitaFederal(String documentoReceitaFederal) {
        this.documentoReceitaFederal = documentoReceitaFederal;
    }

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
