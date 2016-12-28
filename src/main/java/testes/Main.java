package testes;


import modelo.Cliente;
import modelo.Endereco;
import modelo.TipoPessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedido-vendaPU");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction trx = manager.getTransaction();
        trx.begin();

        Cliente cliente = new Cliente();
        cliente.setNome("Lucas Barros");
        cliente.setDocumentoReceitaFederal("123.123.123-12");
        cliente.setEmail("lucas@lucas.barros");
        cliente.setTipo(TipoPessoa.FISICA);

        Endereco endereco = new Endereco();
        endereco.setComplemento("Casa");
        endereco.setCep("56328-120");
        endereco.setBairro("Vila Eduardo");
        endereco.setLogradouro("Rua Dantas Barreto");
        endereco.setCidade("Petrolina");
        endereco.setUf("PE");
        endereco.setNumero("11");
        endereco.setCliente(cliente);

        Endereco endereco1 = new Endereco();
        endereco1.setComplemento("Casa");
        endereco1.setCep("56328-120");
        endereco1.setLogradouro("Rua Pedrinhas");
        endereco1.setCidade("Petrolina");
        endereco1.setUf("PE");
        endereco1.setBairro("Areia Branca");
        endereco1.setNumero("26");
        endereco1.setCliente(cliente);

        cliente.setEnderecos(Arrays.asList(endereco, endereco1));

        if (!cliente.isPersisted())
            manager.persist(cliente);
        else
            manager.merge(cliente);

        trx.commit();

        manager.close();
        factory.close();

        System.out.println("\n Conectado! \n");


    }
}
