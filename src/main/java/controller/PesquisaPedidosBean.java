package controller;

import modelo.Pedido;
import modelo.StatusPedido;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import repository.Pedidos;
import repository.filter.PedidoFilter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Pedidos pedidos;

    private PedidoFilter filtro;
    private LazyDataModel<Pedido> model;

    public PesquisaPedidosBean() {
        filtro = new PedidoFilter();

        model = new LazyDataModel<Pedido>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<Pedido> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

                filtro.setPrimeiroRegistro(first);
                filtro.setQuantidadeRegistr(pageSize);
                filtro.setPropriedadeOrdenacao(sortField);
                filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
                setRowCount(pedidos.quantidadeFiltrados(filtro));

                return pedidos.filtrados(filtro);
            }
        };
    }

    /**
     * TODO métodos
     */

    public void posProcessarXLS(Object documento) {
        HSSFWorkbook planilha = (HSSFWorkbook) documento;
        HSSFSheet folha = planilha.getSheetAt(0);
        HSSFRow cabecalho = folha.getRow(0);
        HSSFCellStyle estiloCelula = planilha.createCellStyle();

        Font fonteCabecalho = planilha.createFont(); // Cria estancia de fonte
        fonteCabecalho.setColor(IndexedColors.WHITE.getIndex());
        fonteCabecalho.setBold(true);
        fonteCabecalho.setFontHeightInPoints((short) 16);

        estiloCelula.setFont(fonteCabecalho);
        estiloCelula.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        estiloCelula.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < cabecalho.getPhysicalNumberOfCells(); i++) {
            cabecalho.getCell(i).setCellStyle(estiloCelula);
        }
    }

    /**
     * geters and sets
     *
     * @return
     */


    public PedidoFilter getFiltro() {
        return filtro;
    }

    public StatusPedido[] getStatusPedidos() {
        return StatusPedido.values();
    }

    public LazyDataModel<Pedido> getModel() {
        return model;
    }
}