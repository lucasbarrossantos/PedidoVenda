package util.mail;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

@RequestScoped
public class Mailer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private SessionConfig config; // Configuração de email da sessão

    public MailMessage novaMensagem() {
        return new MailMessageImpl(this.config).from(this.config.getUsername());
    }

}
