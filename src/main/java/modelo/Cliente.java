package modelo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends EntidadeBase {

    @NotBlank(message = "deve ser informado")
    @Size(max = 60)
    @Column(length = 60, nullable = false)
    private String nome;

    @Email
    @NotBlank(message = "deve ser informado")
    @Column(name = "e_mail", nullable = false)
    private String email;

    @NotBlank(message = "deve ser informado")
    @CPF
    @Size(max = 14)
    @Column(name = "documento_receita_federal", length = 14, nullable = false, unique = true)
    private String documentoReceitaFederal;

    @NotBlank(message = "deve ser informado")
    @CNPJ
    @Size(max = 18)
    @Column(length = 18, unique = true)
    private String cnpj;

    @NotBlank(message = "deve ser informado")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @NotNull(message = "deve ser informado")
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
