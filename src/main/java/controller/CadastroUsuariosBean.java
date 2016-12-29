package controller;

import modelo.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ManagedBean
@SessionScoped
public class CadastroUsuariosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> grupos = new ArrayList<>();
    private String[] grupo = {"Auxiliares", "Vendedores"};

    public CadastroUsuariosBean() {
        Collections.addAll(grupos, grupo);
    }

    public List<String> getGrupos() {
        return grupos;
    }

    public void excluirGrupoDoUsuario (){
        System.out.println("Exclusão de grupo! :P ");
    }
}