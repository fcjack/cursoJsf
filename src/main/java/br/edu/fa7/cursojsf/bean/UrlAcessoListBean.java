package br.edu.fa7.cursojsf.bean;

import br.edu.fa7.cursojsf.model.UrlAcesso;
import br.edu.fa7.cursojsf.service.UrlAcessoService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class UrlAcessoListBean implements Serializable {

    @Inject
    private UrlAcessoService urlAcessoService;

    private List<UrlAcesso> urlAcessoList;

    @PostConstruct
    public void init() {
        urlAcessoList = urlAcessoService.findAll();
    }

    public List<UrlAcesso> getUrlAcessoList() {
        return urlAcessoList;
    }
}
