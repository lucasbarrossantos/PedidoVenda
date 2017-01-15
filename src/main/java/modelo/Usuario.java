package modelo;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeBase {

    @NotBlank(message = "deve ser informado")
    @Size(max = 25, message = "Quantidade máxima é 25 caracteres! Informe o nome e sobre nome.")
    @Column(nullable = false, length = 25)
    private String nome;

    @NotBlank(message = "deve ser informado")
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "deve ser informado")
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String senha;

    /**
     * @JoinTable name = nome da tabela
     * joinColumns = chave estrangeira da tabela origem
     * inverseJoinColumns = chave estrangeira da tabela destino
     */

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id"))
    private List<Grupo> grupos = new ArrayList<>();

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
