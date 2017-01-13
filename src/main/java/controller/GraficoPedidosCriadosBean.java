package controller;

import modelo.Usuario;
import org.primefaces.model.chart.*;
import repository.Pedidos;
import security.UsuarioLogado;
import security.UsuarioSistema;
import util.jsf.FacesUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");

    @Inject
    private Pedidos pedidos;

    @Inject
    @UsuarioLogado
    private UsuarioSistema usuarioLogado;

    private int diaSelecionado;

    private LineChartModel model = new LineChartModel();

    public void preRender() {
        //adicionarSerie("Todos os pedidos", null);
        //adicionarSerie("Meus pedidos", usuarioLogado.getUsuario());
        if (FacesUtil.isNotPostback()){
            FacesUtil.addInfoMessage("Olá " + usuarioLogado.getUsuario().getNome() +
                    "! Selecione um intervalo de dias para visualisar o gráfico");
        }
    }

    @PostConstruct
    public void init() {
        this.model.setTitle("Pedidos criados"); // Título do gráfico
        this.model.setAnimate(true);

        Axis yAxis = this.model.getAxis(AxisType.Y);
        yAxis.setMin(0); // Começa apartir de zero (0)
        yAxis.setLabel("Valores"); // Label lateral esquerda

        model.getAxes().put(AxisType.X, new CategoryAxis("Meses")); // Label na vertical
        model.setShowPointLabels(true);
        yAxis = model.getAxis(AxisType.Y); // Coloca o label na Vertical
        this.model.setLegendPosition("e"); // Posição leste
    }

    private void adicionarSerie(String rotulo, Usuario criadoPor) {
        ChartSeries series = new ChartSeries(rotulo);
        Map<Date, BigDecimal> valoresPorData = this.pedidos.valoresTotaisPorData(diaSelecionado, criadoPor);
        for (Date data : valoresPorData.keySet()) {
            series.set(DATE_FORMAT.format(data), valoresPorData.get(data));
            //series.set("02/01", 52);
            //series.set("02/06", 100);
            //...
        }
        this.model.addSeries(series);
    }

    public int[] getDias() {
        return new int[]{5, 10, 15, 20, 30};
    }

    public int getDiaSelecionado() {
        return diaSelecionado;
    }

    public void setDiaSelecionado(int diaSelecionado) {
        this.diaSelecionado = diaSelecionado;
    }

    public void carregarGrafico() {
        if (diaSelecionado > 0){
            adicionarSerie("Todos os pedidos", null);
            adicionarSerie("Meus pedidos", usuarioLogado.getUsuario());
            FacesUtil.addInfoMessage("Gráfico de vendas de " + diaSelecionado + " dias atrás!");
        }else{
            FacesUtil.addErrorMessage("Selecione um intervalo!");
        }
    }

    public CartesianChartModel getModel() {
        return model;
    }
}
