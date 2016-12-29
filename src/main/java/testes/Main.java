package testes;


import modelo.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedido-vendaPU");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction trx = manager.getTransaction();
        trx.begin();

        Categoria categoriaPai = new Categoria();
        categoriaPai.setDescricao("Computadores");

        Categoria categoriaMesa = new Categoria();
        categoriaMesa.setDescricao("Desktop");
        categoriaMesa.setCategoriaPai(categoriaPai);

        Categoria categoriaPessoal = new Categoria();
        categoriaPessoal.setDescricao("Pessoal");
        categoriaPessoal.setCategoriaPai(categoriaPai);

        categoriaPai.setSubcategorias(Arrays.asList(categoriaMesa,categoriaPessoal));

        Produto produto = new Produto();
        produto.setSku("CME00123");
        produto.setNome("Computador All in One Samsung E3");
        produto.setCategoria(categoriaMesa);
        produto.setQuantidadeEstoque(10);
        produto.setValorUnitario(new BigDecimal(1804.05));

        manager.persist(categoriaPai);
        manager.persist(produto);

        trx.commit();

        manager.close();
        factory.close();

        System.out.println("\n Conectado! \n");


    }
}
