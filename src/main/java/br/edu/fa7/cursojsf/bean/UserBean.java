package br.edu.fa7.cursojsf.bean;

import br.edu.fa7.cursojsf.model.User;
import br.edu.fa7.cursojsf.service.UserService;
import br.edu.fa7.cursojsf.util.Alerta;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@RequestScoped
public class UserBean implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    private Alerta alerta;

    private User user;

    @PostConstruct
    public void init() {
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        if (id != null) {
            user = userService.findById(Integer.parseInt(id));
        } else {
            clear();
        }
    }

    public void save() {
        userService.save(user);
        alerta.info("Usuário salvo com sucesso!");
    }

    public void remove() {
        userService.remove(user.getId());
        clear();
        alerta.info("Usuário removido com sucesso!");
    }

    public User getUser() {
        return user;
    }

    private void clear() {
        user = new User();
    }
}
