package controller;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.impl.templating.velocity.VelocityTemplate;
import modelo.Pedido;

import com.outjected.email.api.MailMessage;
import org.apache.velocity.tools.generic.NumberTool;
import util.jsf.FacesUtil;
import util.mail.Mailer;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Mailer mailer;

    @Inject
    @PedidoEdicao
    private Pedido pedido;

    public void enviarPedido() {
        MailMessage message = mailer.novaMensagem();

        message.to(this.pedido.getCliente().getEmail())
                .subject("Pedido " + this.pedido.getId())
                .charset("utf-8")
                .bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/pedido.template")))
                .put("pedido", this.pedido) // Passando um objeto para o template do VelocityTemplate
                .put("numberTool", new NumberTool())
                .put("locale", new Locale("pt", "BR"))
                .send();

        FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
    }

}
