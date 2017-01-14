package controller;

import org.primefaces.model.chart.PieChartModel;
import repository.Pedidos;
import security.UsuarioLogado;
import security.UsuarioSistema;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Named
@RequestScoped
public class GraficoVendasUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @UsuarioLogado
    private UsuarioSistema vendedor;

    @Inject
    private Pedidos pedidos;

    private PieChartModel model;

    @PostConstruct
    private void init() {
        model = new PieChartModel();

        model.setTitle("Valores de pedidos por usuário");
        model.setLegendPosition("e");
        model.setFill(false);
        model.setShowDataLabels(true);
        model.setDiameter(150);
        model.setSliceMargin(5);
        model.setDataFormat("value");
        model.setDataLabelFormatString("%.2f");

        criarGraficoPier();
    }

    private void criarGraficoPier() {
        Map<String, BigDecimal> valoresPier = pedidos.vendasDeUsuarios();
        for (String s : valoresPier.keySet()) {
            model.set(s, valoresPier.get(s));
        }
    }

    public PieChartModel getModel() {
        return model;
    }
}
