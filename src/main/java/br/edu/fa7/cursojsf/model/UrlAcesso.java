package br.edu.fa7.cursojsf.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "url_acessos")
public class UrlAcesso extends AbstractEntity {

    private String url;
    private Long count;

    public UrlAcesso() {
    }

    public UrlAcesso(String url, Long count) {
        this.url = url;
        this.count = count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void incrementCount() {
        this.count++;
    }
}
