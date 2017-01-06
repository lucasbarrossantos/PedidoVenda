package controller;

import modelo.Grupo;
import modelo.Usuario;
import repository.Grupos;
import repository.Usuarios;
import service.CadastroUsuarioService;
import service.NegocioException;
import util.jsf.FacesUtil;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Grupos grupos;
    @Inject
    private CadastroUsuarioService cadastroUsuarioService;
    @Inject
    private Usuarios usuarios;

    private Usuario usuario;

    private Grupo grupo;
    private Grupo grupoSelecionado;

    private List<Grupo> gruposDoUsuario = new ArrayList<>();
    private List<Grupo> sugestaoGrupos = new ArrayList<>();

    public CadastroUsuarioBean() {
        grupo = new Grupo();
        usuario = new Usuario();
    }

    /**
     * Metodos
     */

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {

            if (usuario == null)
                usuario = new Usuario();

            gruposDoUsuario = usuario.getGrupos();
        }

        sugestaoGrupos = grupos.todos();
    }

    public void salvar() {
        if (gruposDoUsuario.size() > 0)
            usuario.setGrupos(gruposDoUsuario);

        cadastroUsuarioService.salvar(usuario);
        limpar();
        FacesUtil.addInfoMessage("Usuário salvo com sucesso.");
    }

    private void limpar() {
        usuario = new Usuario();
        gruposDoUsuario = new ArrayList<>();
    }

    public void adicionarGrupoAoUsuario() {
        if (gruposDoUsuario.contains(grupo))
            throw new NegocioException("O grupo selecionado já foi adicionado ao usuário.");

        gruposDoUsuario.add(grupo);
    }

    public boolean isEditando() {
        return this.usuario.getId() != null;
    }

    public void removerGrupoDeUsuario() {
        gruposDoUsuario.remove(grupoSelecionado);
    }

    /**
     * geters e sets
     *
     * @return
     */

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Grupo> getGruposDoUsuario() {
        return gruposDoUsuario;
    }

    public List<Grupo> getSugestaoGrupos() {
        return sugestaoGrupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Grupo getGrupoSelecionado() {
        return grupoSelecionado;
    }

    public void setGrupoSelecionado(Grupo grupoSelecionado) {
        this.grupoSelecionado = grupoSelecionado;
    }
}