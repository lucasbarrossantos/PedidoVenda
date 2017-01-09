package controller;

import modelo.Pedido;
import service.EmissaoPedidoService;
import service.NegocioException;
import util.jsf.FacesUtil;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class EmissaoPedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EmissaoPedidoService emissaoPedidoService;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    @Inject
    private Event<PedidoAlteradoEvent> pedidoAlteradoEventEvent;

    public void emitirPedido() {

        if (!this.pedido.isExistente()) {
            throw new NegocioException("O Pedido deve ser salvo antes de tentar emitir!");
        } else {
            this.pedido.removerItemVazio();
            try {
                this.pedido = this.emissaoPedidoService.emitir(this.pedido);
                this.pedidoAlteradoEventEvent.fire(new PedidoAlteradoEvent(this.pedido)); // Passando o novo pedido que acabou de salvar para recuperar na página de CadastroPedido
                FacesUtil.addInfoMessage("Pedido emitido com sucesso.");
            } finally {
                this.pedido.adicionarItemVazio();
            }
        }
    }
}
