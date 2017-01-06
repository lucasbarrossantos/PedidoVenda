package service;

import modelo.Cliente;
import repository.Clientes;
import util.jpa.Transactional;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroClienteService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Clientes clientes;

    @Transactional
    public Cliente salvar(Cliente cliente){
        return clientes.guardar(cliente);
    }
}
