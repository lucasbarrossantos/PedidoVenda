package controller;

import util.jsf.FacesUtil;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Named
@RequestScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FacesContext facesContext;

    @Inject
    private HttpServletRequest request;

    @Inject
    private HttpServletResponse response;

    private String email;

    /**
     * Capturando o parametro da url
     */
    public void preRender() {
        if ("true".equals(request.getParameter("invalid"))) {
            FacesUtil.addErrorMessage("Usuário ou senha inválido!");
        }
    }

    public void login() throws ServletException, IOException {
        // Dispachando a requisição para outro lugar
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.xhtml");
        dispatcher.forward(request, response);

        facesContext.responseComplete();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
