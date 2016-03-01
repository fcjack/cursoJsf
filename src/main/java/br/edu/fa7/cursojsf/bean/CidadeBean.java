package br.edu.fa7.cursojsf.bean;

import br.edu.fa7.cursojsf.model.Cidade;
import br.edu.fa7.cursojsf.model.Estado;
import br.edu.fa7.cursojsf.service.CidadeService;
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
public class CidadeBean implements Serializable {

    @Inject
    private CidadeService cidadeService;

    @Inject
    private EstadoService estadoService;

    @Inject
    private Alerta alerta;

    private Cidade cidade;
    private Integer estadoId;

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String id = request.getParameter("id");
        String estado = request.getParameter("estado");

        if (estado != null) {
            estadoId = Integer.parseInt(estado);
        }

        if (id != null) {
            cidade = cidadeService.findById(Integer.parseInt(id));
        } else {
            cidade = new Cidade();
        }
    }

    public void save() {
        if (cidade.getEstado() == null) cidade.setEstado(new Estado());
        cidade.getEstado().setId(estadoId);
        cidadeService.save(cidade);
        alerta.info("Cidade salva com sucesso!");
    }

    public void remove() {
        cidadeService.remove(cidade.getId());
        alerta.info("Cidade removida com sucesso!");
        cidade = new Cidade();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }
}
