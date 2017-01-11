package controller;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;
import modelo.Cliente;
import modelo.Pedido;
import org.apache.velocity.tools.generic.NumberTool;
import org.primefaces.context.RequestContext;
import util.jsf.FacesUtil;
import util.mail.Mailer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@RequestScoped
public class EnvioClienteEmailBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Mailer mailer;

    @Inject
    @ClienteEmail
    private Cliente cliente;

    public void enviarCliente() {
        MailMessage message = mailer.novaMensagem();

        message.to(this.cliente.getEmail())
                .subject("Olá " + this.cliente.getNome())
                .charset("utf-8")
                .bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/cliente.template")))
                .put("cliente", this.cliente) // Passando um objeto para o template do VelocityTemplate
                .put("numberTool", new NumberTool())
                .put("locale", new Locale("pt", "BR"))
                .send();

        FacesUtil.addInfoMessage("Cliente enviado por e-mail com sucesso!");
    }

}
