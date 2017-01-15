package controller;

import modelo.Grupo;
import org.primefaces.context.RequestContext;
import service.CadastroGrupoService;
import service.NegocioException;
import util.jsf.FacesUtil;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;

@Named
@ViewScoped
public class CadastroGrupoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroGrupoService cadastroGrupoService;

    private Grupo grupo = new Grupo();

    @PostConstruct
    private void init() {
        grupo = new Grupo();
    }


    public void adicionarGrupo() throws NegocioException {
        try {
            this.grupo = this.cadastroGrupoService.adicionar(grupo);
            FacesUtil.addInfoMessage("Grupo " + grupo.getNome() + " adicionado com sucesso!");
            RequestContext.getCurrentInstance().update(Arrays.asList("frm:msg", "frm:grupoDeUsuario"));
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }

    public void prepararGrupo() {
        this.grupo = new Grupo();
    }

    /**
     * @return
     */

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
