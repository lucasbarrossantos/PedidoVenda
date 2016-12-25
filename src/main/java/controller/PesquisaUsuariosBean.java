package controller;

import modelo.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class PesquisaUsuariosBean {

    private Usuario usuario;
    private List<Usuario> usuarios = new ArrayList<>();

    public PesquisaUsuariosBean() {
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("João das Couves");
        usuario.setEmail("joaodas_couves42@hotmail.com");
        usuarios.add(usuario);

        Usuario usuario1 = new Usuario();
        usuario1.setId(2);
        usuario1.setNome("Maria Abadia das Couves");
        usuario1.setEmail("mariaabadiadascouves@gmail.com");
        usuarios.add(usuario1);

        Usuario usuario3 = new Usuario();
        usuario3.setId(2);
        usuario3.setNome("João das Couves Júnior");
        usuario3.setEmail("junior_couves_joao@yahoo.com.br");
        usuarios.add(usuario3);
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}