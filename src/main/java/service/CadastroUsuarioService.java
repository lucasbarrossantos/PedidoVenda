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
    public Usuario salvar(Usuario usuario){
        return usuarios.guardar(usuario);
    }
}
