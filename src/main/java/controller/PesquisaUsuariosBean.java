package controller;

import repository.filter.UsuarioFilter;
import modelo.Usuario;
import repository.Usuarios;
import service.NegocioException;
import util.jsf.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    private Usuario usuarioSelecionado;
    private UsuarioFilter filtro;
    private List<Usuario> usuariosFiltrados;

    public PesquisaUsuariosBean() {
        filtro = new UsuarioFilter();
    }

    public void excluirUsuario() throws NegocioException {
        try {
            usuarios.remover(usuarioSelecionado);
            usuariosFiltrados.remove(usuarioSelecionado);
            FacesUtil.addInfoMessage("Usuário " + usuarioSelecionado.getNome() + " removido com sucesso.");
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }

    public void pesquisar() {
        usuariosFiltrados = usuarios.filtrados(filtro);
    }

    public UsuarioFilter getFiltro() {
        return filtro;
    }

    public List<Usuario> getUsuariosFiltrados() {
        return usuariosFiltrados;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
}