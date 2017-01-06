package repository.filter;


import org.hibernate.validator.constraints.Email;

import java.io.Serializable;

public class UsuarioFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome == null ? null : nome.toUpperCase();
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
