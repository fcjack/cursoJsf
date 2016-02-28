package br.edu.fa7.cursojsf.bean;

import br.edu.fa7.cursojsf.model.Estado;
import br.edu.fa7.cursojsf.service.EstadoService;
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
public class EstadoBean implements Serializable {

    @Inject
    private EstadoService estadoService;
    @Inject
    private Alerta alerta;

    private Estado estado;

    @PostConstruct
    public void init() {
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        if (id != null) {
            estado = estadoService.findById(Integer.parseInt(id));
        } else {
            limpar();
        }
    }

    private void limpar() {
        estado = new Estado();
    }

    public void save() {
        estadoService.save(estado);
        alerta.info("Estado salvo com sucesso!");
    }

    public void remove() {
        estadoService.remove(estado.getId());
        limpar();
        alerta.info("Estado removido com sucesso!");
    }

    public Estado getEstado() {
        return estado;
    }
}
