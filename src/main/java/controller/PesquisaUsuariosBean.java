package controller;

import modelo.Usuario;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuario usuario;
    private List<Usuario> usuarios = new ArrayList<>();

    public PesquisaUsuariosBean() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João das Couves");
        usuario.setEmail("joaodas_couves42@hotmail.com");
        usuarios.add(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setId(2L);
        usuario1.setNome("Maria Abadia das Couves");
        usuario1.setEmail("mariaabadiadascouves@gmail.com");
        usuarios.add(usuario1);

        Usuario usuario3 = new Usuario();
        usuario3.setId(2L);
        usuario3.setNome("João das Couves Júnior");
        usuario3.setEmail("junior_couves_joao@yahoo.com.br");
        usuarios.add(usuario3);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}