package br.edu.fa7.cursojsf.bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class LogoutBean {

    private String mensagem;

    @PostConstruct
    public void logout() throws IOException {
        mensagem = "Volte sempre!";
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) context.getSession(true);
        session.invalidate();
    }

    public String getMensagem() {
        return mensagem;
    }
}
