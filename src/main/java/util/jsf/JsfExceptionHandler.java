package util.jsf;

import service.NegocioException;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.io.IOException;
import java.util.Iterator;

public class JsfExceptionHandler extends ExceptionHandlerWrapper {


    private ExceptionHandler wrapped;

    public JsfExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

        while (events.hasNext()) {
            ExceptionQueuedEvent event = events.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            Throwable exception = context.getException();

            boolean handled = false;
            NegocioException negocioException = getNegocioException(exception);

            try {
                if (exception instanceof ViewExpiredException) {
                    handled = true;
                    redirect("/index.xhtml");
                } else if (negocioException != null) {
                    handled = true;
                    FacesUtil.addErrorMessage(negocioException.getMessage());
                } else {
                    handled = true;
                    redirect("/Erro.xhtml");
                }
            } finally {
                if (handled) {
                    events.remove();
                }
            }
        }

        /**
         * Trata outras exception
         */
        getWrapped().handle();

    }

    private NegocioException getNegocioException(Throwable exception) {
        if (exception instanceof NegocioException) {
            return (NegocioException) exception;
        } else if (exception.getCause() != null) {
            return getNegocioException(exception.getCause());
        }
        return null;
    }

    private void redirect(String page) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            String contextPaph = externalContext.getRequestContextPath();

            externalContext.redirect(contextPaph + page);
            facesContext.responseComplete();
        } catch (IOException e) {
            throw new FacesException(e);
        }
    }
}
