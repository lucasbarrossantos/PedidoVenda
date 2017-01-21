package controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.primefaces.context.RequestContext;
import util.jsf.FacesUtil;
import util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioPedidosEmitidosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date dataInicio;
    private Date dataFim;

    @Inject
    private FacesContext facesContext;

    @Inject
    private HttpServletResponse response;

    @Inject
    private EntityManager manager;

    public void emitir() {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("data_inicio", this.dataInicio);
        parametros.put("data_fim", this.dataFim);

        ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio_pedidos_emitidos.jasper",
                this.response, parametros, "Pedidos emitidos.pdf");

        Session session = manager.unwrap(Session.class);
        session.doWork(executor);

        if (executor.isRelatorioGerado()) {
            FacesUtil.addInfoMessage("Clique no arquivo para ver o resultado da pesquisa");
            RequestContext.getCurrentInstance().update(Arrays.asList(":frm:msg"));
            facesContext.responseComplete();
        } else if (!executor.isRelatorioGerado()){
            FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
        }
    }

    @NotNull
    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @NotNull
    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

}