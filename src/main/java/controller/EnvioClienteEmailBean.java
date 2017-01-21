package controller;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;
import modelo.Cliente;
import modelo.Usuario;
import org.apache.velocity.tools.generic.NumberTool;
import org.hibernate.validator.constraints.Email;
import repository.Usuarios;
import util.jsf.FacesUtil;
import util.mail.Mailer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;
import java.util.Properties;

@Named
@RequestScoped
public class EnvioClienteEmailBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Mailer mailer;

    @Inject
    @ClienteEmail
    private Cliente cliente;

    @Inject
    private Usuarios usuarios;

    @Email(message = "inválido")
    private String email;

    public void enviarCliente() {
        MailMessage message = mailer.novaMensagem();

        message.to(this.cliente.getEmail())
                .subject("Olá " + this.cliente.getNome())
                .bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/cliente.template")))
                .put("cliente", this.cliente) // Passando um objeto para o template do VelocityTemplate
                .put("numberTool", new NumberTool())
                .put("locale", new Locale("pt", "BR"))
                .charset("utf-8")
                .send();

        FacesUtil.addInfoMessage("Os dados do cliente " + cliente.getNome() +
                " foram enviados com sucesso para o endereço " + cliente.getEmail());
    }

    public void recuperar(){
        MailMessage message = mailer.novaMensagem();
        Usuario usuario = usuarios.porEmail(this.email);

        if (usuario != null){
            message.to(this.email)
                    .subject("Olá " + this.email)
                    .bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/recuperarSenhaUsuario.template")))
                    .put("usuario", usuario)
                    .charset("utf-8")
                    .send();
            FacesUtil.addInfoMessage("Email enviado com sucesso!");
            this.email = null;
        }else{
            FacesUtil.addErrorMessage("Não há registro de usuário com o e-mail " + this.email);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
