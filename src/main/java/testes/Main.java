package testes;


import modelo.*;
import modelo.vo.DataValor;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pedido-vendaPU");
        EntityManager manager = factory.createEntityManager();
        Session session = manager.unwrap(Session.class);

        Usuario usuario = manager.find(Usuario.class, 1L);

        Map<Date, BigDecimal> valores = valoresTotaisPorData(15, usuario, session);

        for (Date data : valores.keySet()){
            System.out.println(data  + " = " + valores.get(data));
        }

    }

    @SuppressWarnings("unchecked")
    private static Map<Date, BigDecimal> valoresTotaisPorData(Integer numeroDias, Usuario criadoPor, Session session) {
        //numeroDias -= 1;

        Calendar dataInicial = Calendar.getInstance(); // Data do sistema

        dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH); // trunc para ficar sem hora, minuto e segundo
        dataInicial.add(Calendar.DAY_OF_MONTH, numeroDias * -1); // -1 para dizer os dias para trás.

        Map<Date, BigDecimal> resultado = criarMapaVazio(numeroDias, dataInicial);

        Criteria criteria = session.createCriteria(Pedido.class);

        //select date(data_criacao) as data, sum(valor_total) as Valor
        // from pedido where data_criacao>= :datainicial
        // group by date(data_criacao)

        criteria.setProjection(Projections.projectionList()
                .add(Projections.sqlGroupProjection("date(data_criacao) as data",
                        "date(data_criacao)",
                        new String[]{"data"}, new Type[]{StandardBasicTypes.DATE}))
                .add(Projections.sum("valorTotal").as("Valor"))
        ).add(Restrictions.ge("dataCriacao", dataInicial.getTime()));

        if (criadoPor != null) {
            criteria.add(Restrictions.eq("vendedor", criadoPor));
        }

        @SuppressWarnings("unchecked")
        List<DataValor> valoresPorData = criteria.
                setResultTransformer(Transformers.aliasToBean(DataValor.class)).list();

        for (DataValor dataValor : valoresPorData) {
            resultado.put(dataValor.getData(), dataValor.getValor());
        }

        return resultado;
    }

    static Map<Date, BigDecimal> criarMapaVazio(Integer numeroDias, Calendar dataInicial) {

        dataInicial = (Calendar) dataInicial.clone();
        Map<Date, BigDecimal> mapaInicial = new TreeMap<>();

        for (int i = 0; i < numeroDias; i++) {
            mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO); // Mapa vazio getTime(), retorna um Date
            dataInicial.add(Calendar.DAY_OF_MONTH, 1); // Adiciona 1 dia
        }
        return mapaInicial;
    }

}
