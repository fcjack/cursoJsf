package br.edu.fa7.cursojsf.bean;

import br.edu.fa7.cursojsf.model.User;
import br.edu.fa7.cursojsf.service.UserService;
import br.edu.fa7.cursojsf.util.Alerta;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class LoginBean {

    @Inject
    private UserService userService;

    @Inject
    private Alerta alerta;

    private String username;
    private String password;

    public void login() throws IOException {
        User user = userService.findByUsernameAndPassword(username, password);

        if (user == null) {
            alerta.error("Usuário e/ou senha inválido(s). (Padrão = usuário: 'admin', senha: 'admin')");
        } else {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) context.getSession(true);
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            session.setAttribute("userLogged", username);

            context.redirect(request.getContextPath());

        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
