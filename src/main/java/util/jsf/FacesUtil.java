package util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

class FacesUtil {

    static void addErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }
}