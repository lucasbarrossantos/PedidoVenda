package controller;

import modelo.Pedido;
import service.CancelamentoPedidoService;
import service.NegocioException;
import util.jsf.FacesUtil;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class CancelamentoPedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CancelamentoPedidoService cancelamentoPedidoService;

    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEventEvent; // Lanca um evento quando o pedido for alterado

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    public void cancelarPedido() throws NegocioException {
        try {
            this.pedido = cancelamentoPedidoService.cancelar(this.pedido);
            this.pedidoAlteradoEventEvent.fire(new PedidoAlteradoEvent(this.pedido));

            FacesUtil.addInfoMessage("Pedido cancelado com sucesso!");
        } catch (NegocioException e) {
            FacesUtil.addErrorMessage(e.getMessage());
        }
    }

}
