package testes;

import modelo.Usuario;
import modelo.vo.GraficoValorUsuario;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedido-vendaPU");
        EntityManager manager = factory.createEntityManager();
        Session session = manager.unwrap(Session.class);

        Usuario usuario = manager.find(Usuario.class, 1L);

        Map<String, BigDecimal> resultado = new TreeMap<>();

        List<GraficoValorUsuario> valorUsuario;

        valorUsuario = manager.createQuery("select new modelo.vo.GraficoValorUsuario(p.vendedor as usuario, sum(p.valorTotal) as valor) " +
                "from Pedido p where p.vendedor = :vendedor group by p.vendedor", GraficoValorUsuario.class)
                .setParameter("vendedor", usuario)
                .getResultList();

        for (GraficoValorUsuario queryResult : valorUsuario) {
            resultado.put(queryResult.getUsuario().getNome(), queryResult.getValor());
        }


        System.out.println(resultado);

    }
}
