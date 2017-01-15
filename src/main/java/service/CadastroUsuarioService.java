package service;

import modelo.Usuario;
import repository.Usuarios;
import util.jpa.Transactional;
import javax.inject.Inject;
import java.io.Serializable;

public class CadastroUsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    @Transactional
    public Usuario salvar(Usuario usuario) throws NegocioException {

        Usuario clone = usuarios.porEmail(usuario.getEmail());

        if (clone != null && usuario.getId() == null)
            throw new NegocioException("Já existe um usuário cadastrado com o e-mail " + usuario.getEmail() + ".");

        return usuarios.guardar(usuario);
    }
}
