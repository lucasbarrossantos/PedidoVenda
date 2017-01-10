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
        Cliente cl = this.clientes.porCPF(cliente.getDocumentoReceitaFederal());

        if (cl != null && cliente.getId() == null){
            throw new NegocioException("Já existe um cliente cadastrado com o CPF " + cliente.getDocumentoReceitaFederal());
        }

        return clientes.guardar(cliente);
    }
}
