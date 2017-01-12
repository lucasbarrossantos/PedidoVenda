package security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Seguranca {

    @Inject
    private ExternalContext externalContext;

    public String getNomeUsuario() {
        String nome = null;
        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();
        }

        return nome;
    }

    public UsuarioSistema getUsuarioLogado() {
        UsuarioSistema usuario = null;

        /**
         * Retorne um objeto do tipo {@link UsuarioSistema}
         */
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)
                FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (auth != null && auth.getPrincipal() != null) {
            usuario = (UsuarioSistema) auth.getPrincipal();
        }

        return usuario;
    }

    public boolean isEmitirPedidoPermitido() {
        // Para saber se o usuário logado está no grudo de admin
        return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("VENDEDORES");
    }

    public boolean isCancelarPedidoPermitido() {
        // Para saber se o usuário logado está no grudo de admin
        return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("VENDEDORES");
    }

    public boolean isSalvarCadastroCliente(){
        return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("VENDEDORES");
    }
}
