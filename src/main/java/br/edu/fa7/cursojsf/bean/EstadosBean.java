package br.edu.fa7.cursojsf.bean;

import br.edu.fa7.cursojsf.model.Estado;
import br.edu.fa7.cursojsf.service.EstadoService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class EstadosBean implements Serializable {

    @Inject
    private EstadoService estadoService;

    private List<Estado> estados;

    @PostConstruct
    public void init() {
        estados = estadoService.findAll();
    }

    public List<Estado> getEstados() {
        return estados;
    }
}
