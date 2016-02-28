package br.edu.fa7.cursojsf.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Estado extends AbstractEntity {

    private String nome;
    private String sigla;

    @OneToMany(mappedBy = "estado", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Cidade> cidades;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
