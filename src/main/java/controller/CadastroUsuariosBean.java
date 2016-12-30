package controller;

import modelo.Usuario;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named
@ViewScoped
public class CadastroUsuariosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuario usuario;


    private List<String> grupos = new ArrayList<>();
    private String[] grupo = {"Auxiliares", "Vendedores"};

    public CadastroUsuariosBean() {
        Collections.addAll(grupos, grupo);
    }

    public void salvar(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);
        usuarios.forEach(u -> System.out.println("Nome: " + u.getNome() + ", e-mail: " + u.getEmail()));
        usuarios.clear();
        usuario = new Usuario();
    }

    public List<String> getGrupos() {
        return grupos;
    }

    public void excluirGrupoDoUsuario() {
        System.out.println("Exclusão de grupo! :P ");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}